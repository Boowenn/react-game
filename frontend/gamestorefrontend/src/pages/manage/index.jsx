import { useState, useEffect, useRef } from 'react';
import Header from '../../components/header';
import Tabs from '../../components/tabs';
import ReactTable from 'react-table'
import axios from 'axios';
import message from '../../components/message';
import { history } from 'umi';
import Popup from 'react-popup';

import '../../popup.css';
import styles from './index.less';

export default function ManagePage() {

  const [init, setInit] = useState(true);
  const [index, setIndex] = useState(0);
  const [data, setData] = useState([]);
  const [name, setName] = useState('');
  const [provinceCode, setProvinceCode] = useState('');
  const [provinceName, setProvinceName] = useState('');
  const [cityCode, setCityCode] = useState('');
  const [cityName, setCityName] = useState('');
  const [areaCode, setAreaCode] = useState('');
  const [areaName, setAreaName] = useState('');
  const [zip, setZip] = useState('');
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const [visible, setVisible] = useState(false);
  const [defaultVal, setDefault] = useState(1);
  const [user, setUser] = useState('');

  const [proList, setProList] = useState([]);
  const [cityList, setCityList] = useState([]);
  const [areaList, setAreaList] = useState([]);

  /**收货地址相关 */
  const inputName = useRef();
  const inputProvinceCode = useRef();
  const inputCityCode = useRef();
  const inputAreaCode = useRef();
  const inputZip = useRef();
  const inputAddress = useRef();
  const inputPhone = useRef();

  /**密码相关 */
  const inputPwd = useRef();
  const inputNewPwd = useRef();
  const inputConfirmPwd = useRef()

  /**个人资料相关 */
  const inputPhonePerson = useRef();
  const inputEmailPerson = useRef();
  const inputGenderPerson = useRef()

  const handleChangePro = (e) => {
    const id = proList.find(v => v.name === e.target.value).code;
    getCityList(id);
  }

  const handleChangeCity = (e) => {
    const id = cityList.find(v => v.name === e.target.value).code;
    getAreaList(id);
  }

  useEffect(() => {
    axios.get('/api/address').then(res => {
      setData(res.data.data || []);
    }, err => {
      console.log(err);
      if (err && err.response.data === '登录已过期') {
        message.error('请先登录');
        sessionStorage.removeItem('token');
        setTimeout(() => {
          history.replace('/login');
        }, 3000)
      }
    })

    axios.get('/api/district/parent?parent=86').then(res => {
      console.log(res);
      setProList(res.data.data || []);
    }, err => {
    })
  }, [init])

  const getCityList = (id) => {
    axios.get('/api/district/parent?parent=' + id).then(res => {
      console.log(res);
      setCityList(res.data.data || []);
    }, err => {
    })
  }

  useEffect(() => {
    if (!proList || !proList.length) return;
    getCityList(proList[0].code);
  }, [proList])

  useEffect(() => {
    const token = sessionStorage.getItem('token') ? JSON.parse(sessionStorage.getItem('token')) : null;
    setUser(token ? token.username : '');
  }, [])

  const getAreaList = (id) => {
    axios.get('/api/district/parent?parent=' + id).then(res => {
      console.log(res);
      setAreaList(res.data.data || []);
    }, err => {
    })
  }

  useEffect(() => {
    if (!cityList || !cityList.length) return;
    getAreaList(cityList[0].code)
  }, [cityList])

  const handleDelete = (item) => {

    let mySpecialPopup = Popup.register({
      title: '确定要删除吗?',
      content: '确定要删除吗?',
      buttons: {
        left: ['取消'],
        right: [{
          text: '确定',
          key: '⌘+s',
          className: 'success',
          action: function () {
            axios.post('/api/address/delete_address/' + item.aid).then(res => {
              console.log(res);
              setInit(!init);
            }, err => {
            })
            Popup.close();
          }
        }]
      }
    });
    Popup.queue(mySpecialPopup);
  }

  const handleAdd = () => {
    setVisible(true);
  }

  const columns = [{
    Header: '地址类型',
    accessor: 'aid',
  },
  {
    Header: '收货人姓名',
    accessor: 'name'
  },
  {
    Header: '详细地址',
    Cell: row => (
      <div>
        {row.original.provinceName + row.original.cityName + row.original.areaName + row.original.address}
      </div>
    )
  },
  {
    Header: '联系电话',
    accessor: 'phone',
  },
  {
    Header: '操作',
    Cell: row => (
      <div>
        <button onClick={() => handleDelete(row.original)}>Delete</button>
      </div>
    )
  }];

  return (
    <div>
      { Header() }
      <Tabs>
        <div label="修改密码">
          <div>
            原密码：<input type="text" ref={inputPwd} />
          </div>
          <div>
            新密码：<input type="text" ref={inputNewPwd} />
          </div>
          <div>
            确认密码：<input type="text" ref={inputConfirmPwd} />
          </div>
          <button className={styles.commonbtn} onClick={() => {
            if (!inputPwd.current.value) {
              message.error('请输入原密码');
              return;
            }

            if (!inputNewPwd.current.value) {
              message.error('请输入新密码');
              return;
            }

            if (!inputConfirmPwd.current.value) {
              message.error('请输入确认密码');
              return;
            }

            if (inputConfirmPwd.current.value !== inputNewPwd.current.value) {
              message.error('确认密码与新密码不一致');
              return;
            }

            let formData = new FormData();
            formData.append('oldPassword', inputPwd.current.value);
            formData.append('newPassword', inputNewPwd.current.value);

            axios.post('/api/user/change_password', formData).then(res => {

              if (res.data.state === 200) {
                message.success('修改密码成功，请重新登录');
                sessionStorage.removeItem('token');
                history.replace('/login');
              } else {
                message.error(res.data.message);
              }
            })

          }}>
            <span>确认修改</span>
          </button>
        </div>
        <div label="个人资料">
          <div>
            用户名: <input type="text" value={user} disabled />
          </div>
          <div>
            手机号码: <input type="text" ref={inputPhonePerson} />
          </div>
          <div>
            电子邮箱：<input type="text" ref={inputEmailPerson} />
          </div>
          <div>
            性别
            <input type="radio" name="sex" value="1" id="man" checked={defaultVal === 1} onChange={() => {
              setDefault(1)
            }}/><label>男</label>
            <input type="radio" name="sex" value="0" id="women" checked={defaultVal === 0} onChange={() => {
              setDefault(0)
            }} /><label>女</label>
          </div>
          <button className={styles.commonbtn} onClick={() => {
            if (!inputPhonePerson.current.value) {
              message.error('请输入手机号码');
              return;
            }

            if(!/^[1][3,4,5,7,8][0-9]{9}$/.test(inputPhonePerson.current.value)) {
              message.error('手机号码不符合要求');
              return;
            }

            if (!inputEmailPerson.current.value) {
              message.error('请输入邮箱');
              return;
            }

            if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(inputEmailPerson.current.value)) {
              message.error('邮箱格式不符合要求');
              return;
            }

            let formData = new FormData();
            formData.append('phone', inputPhonePerson.current.value);
            formData.append('email', inputEmailPerson.current.value);
            formData.append('gender', defaultVal);

            axios.post('/api/user/change_info', formData).then(res => {

              if (res.data.state === 200) {
                message.success('修改信息成功');
              } else {
                message.error(res.data.message);
              }
            })

          }}>
            <span>确认修改</span>
          </button>
        </div>
        <div label="收货地址">
          <ReactTable 
            columns={columns} 
            data={data} 
            defaultPageSize={10} 
            minRows={3}
          ></ReactTable>
          <Popup />
          <div className={styles.footer}>
            <button onClick={() => handleAdd()}>新增</button>
          </div>
        </div>
      </Tabs>
      {
        visible && <div className={styles.modal}>
          <div className={styles.bg}></div>
          <div className={styles['modal-box']}>
            <div className={styles.header}>
              新增收货地址
            </div>
            <div>
            <div className={styles.form}>
              <div>
                <span className={styles.label}>收货人</span>
                <input type="text" ref={inputName}/>
              </div>
              <div>
                <span className={styles.label}>省</span>
                <select onChange={(e) => handleChangePro(e)} ref={inputProvinceCode}>
                  {
                    proList.length && proList.map(v => (
                      <option>{v.name}</option>
                    ))
                  }
                </select>
              </div>
              <div>
                <span className={styles.label}>城市</span>
                <select onChange={(e) => handleChangeCity(e)} ref={inputCityCode}>
                  {
                    cityList.length && cityList.map(v => (
                      <option>{v.name}</option>
                    ))
                  }
                </select>
              </div>
              <div>
                <span className={styles.label}>区/县</span>
                <select ref={inputAreaCode}>
                  {
                    areaList.length && areaList.map(v => (
                      <option>{v.name}</option>
                    ))
                  }
                </select>
              </div>
              <div>
                <span className={styles.label}>邮编</span>
                <input type="text" ref={inputZip} />
              </div>
              <div>
                <span className={styles.label}>详细地址</span>
                <input type="text" ref={inputAddress}/>
              </div>
              <div>
                <span className={styles.label}>手机</span>
                <input type="text" ref={inputPhone}/>
              </div>
            </div>
            </div>
            <div className={styles['modal-footer']}>
              <button className={styles.normal} onClick={() => {
                setVisible(false);
              }}>
                <span>取消</span>
              </button>
              <button className={styles.blue} onClick={() => {
                if (!inputName.current.value) {
                  message.error('收货人不能为空');
                  return;
                }

                if (!inputZip.current.value) {
                  message.error('邮编不能为空');
                  return;
                }

                if (!inputAddress.current.value) {
                  message.error('详细地址不能为空');
                  return;
                }

                if (!inputPhone.current.value) {
                  message.error('手机不能为空');
                  return;
                }

                const proCodeLen = proList.find(v => v.name === inputProvinceCode.current.value).code;
                const cityCodeLen = cityList.find(v => v.name === inputCityCode.current.value).code;
                const areaCodeLen = areaList.find(v => v.name === inputAreaCode.current.value).code;

                let formData = new FormData();
                formData.append('name', inputName.current.value);
                formData.append('provinceCode', proCodeLen);
                formData.append('provinceName', inputProvinceCode.current.value);
                formData.append('cityCode', cityCodeLen);
                formData.append('cityName', inputCityCode.current.value);
                formData.append('areaCode', areaCodeLen);
                formData.append('areaName', inputAreaCode.current.value);
                formData.append('zip', inputZip.current.value);
                formData.append('address', inputAddress.current.value);
                formData.append('phone', inputPhone.current.value);

                axios.post('/api/address', formData).then(res => {
                  console.log(res);

                  if (res.data.state === 200) {
                    message.success('新增成功');
                    setVisible(false);
                    setInit(!init);
                  } else {
                  }
                })
              }}>
                <span>新增</span>
              </button>
            </div>
          </div>
        </div>
      }
    </div>
  )
}