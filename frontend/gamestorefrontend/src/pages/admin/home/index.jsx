import { useState, useEffect, useRef } from 'react';
import ReactTable from 'react-table'
import axios from 'axios';
import { history } from 'umi';
import { assetAPI } from '../../../utils/index';
import message from '../../../components/message';
import Popup from 'react-popup';

import '../../../popup.css';
import styles from './index.less';

export default function HomePage() {

  const [current, setCurrent] = useState(0);
  const [good, setGood] = useState([]);
  const [user, setUser] = useState([]);
  const [total, setTotal] = useState(10000);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10000);
  const [userTotal, setUserTotal] = useState(10000);
  const [userPage, setUserPage] = useState(0);
  const [userSize, setUserSize] = useState(10000);
  const [visible, setVisible] = useState(false);
  const [init, setInit] = useState(true);
  const [fileData, setFileData] = useState(null);
  const [imgUpload, setImgUpload] = useState('');
  const [isEdit, setIsEdit] = useState(false);
  const [detail, setDetail] = useState({
    title: '',
    num: 0,
    price: '',
  })
  const list = ['商品管理', '用户管理'];

  const inputName = useRef();
  const inputPrize = useRef();
  const inputNum = useRef();

  const inputFile = useRef();

  useEffect(() => {
    axios.get(`/api/product/all_list/${page}/${size}`).then(res => {
      if (res.data.message === "admin已失效") {
        history.replace('/admin/login');
        message.error('登录已过期，请重新登录');
        return;
      }

      setGood(res.data.data.list || []);
      // setTotal(res.data.data.total);
    })
  }, [init])

  useEffect(() => {
    axios.get(`/api/user/getUser/${userPage}/${userSize}`).then(res => {
      if (res.data.message === "admin已失效") {
        history.replace('/admin/login');
        message.error('登录已过期，请重新登录');
        return;
      }

      setUser(res.data.data.list || []);
    })
  }, [init])

  // const onStateChange = useCallback(({ pageIndex, pageSize }) => {
  //   fetchOrders({
  //     page: pageIndex,
  //     size: pageSize
  //   })
  // }, [])

  const onStateChange = () => {
    console.log('改变');
  }


  const columns = [{
    Header: '图片',
    Cell: row => {
      const imgUrl = assetAPI + '' + row.original.image + 'collect.png';
      return (
        <div>
          <img className={styles.innerImg} src={row.original.imageUrl} alt=""/>
        </div>
      )
    }
  },
  {
    Header: '商品',
    accessor: 'title'
  },
  {
    Header: '单价',
    accessor: 'price'
  },
  {
    Header: '库存数量',
    accessor: 'num'
  },
  {
    Header: '状态',
    Cell: row => (
      <div>
        {{
          1: '已上架',
          2: '已下架',
          3: '已删除',
        }[row.original.status]}
      </div>
    )
  },
  {
    Header: '操作',
    Cell: row => (
      <div>
        <button onClick={() => {
          let mySpecialPopup = Popup.register({
            title: '确定要删除该商品吗?',
            content: '确定要删除该商品吗?',
            buttons: {
              left: ['取消'],
              right: [{
                text: '确定',
                key: '⌘+s',
                className: 'success',
                action: function () {
                  let formData = new FormData();
                  formData.append('id', row.original.id);

                  axios.post(`/api/product/delete`, formData).then(res => {

                    if (res.data.message === "admin已失效") {
                      history.replace('/admin/login');
                      message.error('登录已过期，请重新登录');
                      return;
                    }
                    
                    message.error(res.data.message || '');
                    setInit(!init);
                  })
                  Popup.close();
                }
              }]
            }
          });
          Popup.queue(mySpecialPopup);
        }}>Delete</button>
        <button onClick={() => {
          const item = {
            id: row.original.id,
            num: row.original.num,
            title: row.original.title,
            price: row.original.price,
          };
          setDetail(item);
          setImgUpload(row.original.imageUrl);
          setIsEdit(true);
          setVisible(true);
        }}>edit</button>
      </div>
    )
  }];

  const userColumns = [
  {
    Header: '用户名',
    accessor: 'username'
  },
  {
    Header: '操作',
    Cell: row => (
      <div>
        <button onClick={() => {

          let mySpecialPopup = Popup.register({
            title: '确定要删除该用户吗?',
            content: '确定要删除该用户吗?',
            buttons: {
              left: ['取消'],
              right: [{
                text: '确定',
                key: '⌘+s',
                className: 'success',
                action: function () {
                  let formData = new FormData();
                  formData.append('uid', row.original.uid);

                  axios.post(`/api/user/delete_user`, formData).then(res => {

                    if (res.data.message === "admin已失效") {
                      history.replace('/admin/login');
                      message.error('登录已过期，请重新登录');
                      return;
                    }
                    
                    message.error(res.data.message || '');
                    setInit(!init);
                  })
                  Popup.close();
                }
              }]
            }
          });
          Popup.queue(mySpecialPopup);
        }}>Delete</button>
      </div>
    )
  }];

  const handleFileChange = (file, files) => {
    setFileData(file.target.files[0]);
  }


  return (
    <div>
      {
        visible && <div className={styles.modal}>
          <div className={styles.bg}></div>
          <div className={styles['modal-box']}>
            <div className={styles.headers}>
              {
                isEdit ? '编辑':'新增'
              }商品
            </div>
            <div>
            <div className={styles.form}>
              <div>
                <span className={styles.label}>商品名称</span>
                <input type="text" value={detail.title} onChange={(e) => {
                  setDetail({
                    ...detail,
                    title: e.target.value,
                  })
                }} ref={inputName}/>
              </div>
              <div>
                <span className={styles.label}>图片</span>
                {
                  imgUpload && <img src={imgUpload} className={styles.imgUp}></img> 
                }
                <input type="file" ref={inputFile} onChange={(file, files) => {
                  handleFileChange(file, files)
                }}/>
                <button onClick={() => {

                  if (!fileData) {
                    message.error('还未添加图片');
                    return;
                  }

                  let formData = new FormData();
                  formData.append('file', fileData);

                  axios.post(`/api/file/upload`, formData).then(res => {

                    if (res.data.message === "admin已失效") {
                      history.replace('/admin/login');
                      message.error('登录已过期，请重新登录');
                      return;
                    }

                    setImgUpload(`${assetAPI}${res.data.message}`);
                    
                    // message.error(res.data.message || '');
                    // setInit(!init);
                  })
                }}>
                  <span>上传</span>
                </button>
              </div>
              <div>
                <span className={styles.label}>单价</span>
                <input type="text" value={detail.price} onChange={(e) => {
                  setDetail({
                    ...detail,
                    price: e.target.value,
                  })
                }} ref={inputPrize}/>
              </div>
              <div>
                <span className={styles.label}>库存数量</span>
                <input type="text" value={detail.num} onChange={(e) => {
                  setDetail({
                    ...detail,
                    num: e.target.value,
                  })
                }} ref={inputNum}/>
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
                  message.error('商品名称不能为空');
                  return;
                }

                if (!imgUpload) {
                  message.error('请上传图片');
                  return;
                }

                if (!inputPrize.current.value) {
                  message.error('商品价格不能为空');
                  return;
                }

                if (!inputNum.current.value) {
                  message.error('库存数量不能为空');
                  return;
                }

                if (isEdit) {
                  let formData = new FormData();
                  formData.append('id', detail.id);
                  formData.append('title', inputName.current.value);
                  formData.append('imageUrl', imgUpload);
                  formData.append('price', inputPrize.current.value.toString());
                  formData.append('num', inputNum.current.value);
                  axios.post(`/api/product/update`, formData).then(res => {

                    if (res.data.message === "admin已失效") {
                      history.replace('/admin/login');
                      message.error('登录已过期，请重新登录');
                      return;
                    }
  
                    if (res.data.state === 200) {
                      message.success('修改商品成功');
                      setVisible(false);
                      setInit(!init);
                      return;
                    }
                    
                    
                    message.error(res.data.message || '');
                  })
                } else {
                  let formData = new FormData();
                  formData.append('title', inputName.current.value);
                  formData.append('imageUrl', imgUpload);
                  formData.append('price', inputPrize.current.value.toString());
                  formData.append('num', inputNum.current.value);
                  axios.post(`/api/product/add`, formData).then(res => {

                    if (res.data.message === "admin已失效") {
                      history.replace('/admin/login');
                      message.error('登录已过期，请重新登录');
                      return;
                    }
  
                    if (res.data.state === 200) {
                      message.success('新增商品成功');
                      setVisible(false);
                      setInit(!init);
                      return;
                    }
                    
                    
                    message.error(res.data.message || '');
                  })
                }


              }}>
                <span>确定{isEdit ? '修改' : '新增'}</span>
              </button>
            </div>
          </div>
        </div> 
      }
      <div className={styles.header}>
        <ul>
          {
            list.map((v, key) => (
              <li style={{ opacity: key === current ? '1' : '0.7' }} key={key} onClick={() => {
                setCurrent(key);
              }}>{v}</li>
            ))
          }
        </ul>
      </div>
      <div>
        {
          current === 0 && <div>
            <div className={styles.head}>
              <button onClick={() => {
                setVisible(true);
                setIsEdit(false);
              }}>
                <span>新增商品</span>
              </button>
            </div>
            <ReactTable 
              columns={columns} 
              data={good} 
              defaultPageSize={10} 
              minRows={3}
              // pages={page}
              // showPagination={true}
              // totalCount={total}
              // onStateChange={onStateChange}
              // onFetchData={(state, instance) => {
              //   console.log('====>切换')
              // }}
            ></ReactTable>
            <Popup />
          </div>
        }
        {
          current === 1 && <div>
            <ReactTable 
              columns={userColumns} 
              data={user} 
              defaultPageSize={10} 
              minRows={3}
            ></ReactTable>
            <Popup />
          </div>
        }
      </div>
    </div>
  )
}