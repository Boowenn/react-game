import { useState, useEffect } from 'react';
import Header from '../../components/header';
import message from '../../components/message';
import ReactTable from 'react-table'
import { history } from 'umi';
import axios from 'axios';
import { assetAPI } from '../../utils/index';
import Popup from 'react-popup';

import '../../popup.css';
import "react-table/react-table.css"
import styles from './index.less';

import wx from '../../assets/wx.png';
import ali from '../../assets/ali.png';
import hua_bei from '../../assets/hua_bei.png';
import circle from '../../assets/circle.png';
import select from '../../assets/select.png';

export default function ShopPage() {

  const [data, setData] = useState([]);
  const [init, setInit] = useState(true);
  const [visible, setVisible] = useState(false);
  const [selectIndex, setSelectIndex] = useState(0);
  const [success, setSuccess] = useState(false);
  const [store, setStore] = useState([]);
  const [address, setAddress] = useState([]);
  const [keyIndex, setKeyIndex] = useState(0);

  useEffect(() => {
    const isLogin = sessionStorage.getItem('isLogin') || false;

    if (!isLogin) {
      message.error('请先登录');
      setTimeout(() => {
        history.replace('/login');
      }, 3000)
      return;
    }

    axios.get('/api/cart/show_carts').then(res => {
      console.log('====购物车>');
      console.log(res);

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

    axios.get('/api/address').then(res => {
      console.log(res.data.data);
      setAddress(res.data.data || []);
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
  }, [init]);

  const handleFinish = () => {
    if (!data || !data.length) {
      alert('购物车无商品，请先添加商品');
      return;
    }

    setVisible(true);
  }

  const handleDelete = (record) => {
    console.log(record);

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
            let formData = new FormData();
            formData.append('cid', record.cid);

            axios.post('/api/cart/delete_cart', formData).then(res => {
              if (res.data.state === 200) {
                setInit(!init)
              }
            })
            Popup.close();
          }
        }]
      }
    });
    Popup.queue(mySpecialPopup);
  }

  const handleAdd = (item) => {
    let formData = new FormData();
    formData.append('cid', item.cid);
    formData.append('num', item.num + 1);

    axios.post('/api/cart/update_num', formData).then(res => {
      if (res.data.state === 200) {
        setInit(!init)
      }
    })
  }

  const handleRelease = (item) => {

    if (item.num <= 1) {
      message.error('商品最少得选一个');
      return;
    }

    let formData = new FormData();
    formData.append('cid', item.cid);
    formData.append('num', item.num - 1);

    axios.post('/api/cart/update_num', formData).then(res => {
      if (res.data.state === 200) {
        setInit(!init)
      }
    })

  }

  const columns = [{
    Header: '图片',
    Cell: row => {
      const imgUrl = assetAPI + '/' + row.original.image + 'collect.png';
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
    Header: '数量',
    Cell: row => (
      <div className={styles.inputNum}>
        <button onClick={() => handleRelease(row.original)}>-</button>
        <p>{row.original.num}</p>
        <button onClick={() => handleAdd(row.original)}>+</button>
      </div>
    )
  },
  {
    Header: '金额',
    Cell: row => (
      <div>
        <p>{row.original.price * row.original.num}</p>
      </div>
    )
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
      <div className={styles.box}>
        <h1 className={styles.h1}>购物车</h1>
        <ReactTable 
          columns={columns} 
          data={data} 
          defaultPageSize={10} 
          minRows={3}
        ></ReactTable>
        <div className={styles.footer}>
          <div>
            地址
            <ul style={{
              display: 'flex',
              flexDirection: 'column'
            }}>
              {
                address && address.map((v, key) => (
                  <li style={{border: '1px solid skyblue', width: '100%', padding: 20, paddingRight: 60, borderRadius: 10, marginBottom: 6, display: 'flex',justifyContent: 'space-between'}} onClick={() => {
                    setKeyIndex(key);
                  }}>
                    <div>
                      <div>
                        {v.provinceName}{v.cityName}{v.areaName}{v.address} 
                      </div>
                      <span>
                        收货人姓名：{v.name};  联系电话：{v.phone}
                      </span>
                    </div>
                    <img src={keyIndex === key ? select : circle} style={{marginLeft: 'auto', width: 20, height: 20, display: 'inline-block'}} alt=""/>
                  </li> 
                ))
              }
            </ul>
          </div>
          <button onClick={() => handleFinish()}>结算</button>
        </div>
        <Popup />
      </div>

      {
        visible && <div className={styles.modal}>
          <div className={styles.bg}></div>
          <div className={styles['modal-box']}>
            <div className={styles.header}>
              请选择支付方式
            </div>
            <div className={styles.form}>
              <ul>
                {
                  ['微信支付', '支付宝支付', '花呗支付'].map((v, key) => (
                    <li onClick={() => {
                      setSelectIndex(key);
                    }}>
                      <div>
                        <img src={{
                          0: wx,
                          1: ali,
                          2: hua_bei
                        }[key]} alt=""/>
                        <span>{v}</span>  
                      </div> 
                      <img class="right" src={selectIndex === key ? select : circle} alt=""/>
                    </li>
                  ))
                }
              </ul>
            </div>
            <div className={styles['modal-footer']}>
              <button className={styles.normal} onClick={() => {
                setVisible(false);
              }}>
                <span>取消</span>
              </button>
              <button className={styles.blue} onClick={() => {
                setStore(JSON.parse(JSON.stringify(data)))
                const strem = data.map(v => {
                  return new Promise((resolve, reject) => {
                    let formData = new FormData();
                    formData.append('cid', v.cid);
                    return axios.post('/api/cart/delete_cart', formData).then(res => {
                      if (res.data.state === 200) {
                        resolve();
                      }
                    })
                  })
                })

                Promise.all(strem).then(res => {
                  console.log('执行成功=====>');
                  setVisible(false);
                  setSuccess(true);
                  setInit(!init);
                  
                  console.log(data);

                  const storeData = data.map(v => (
                    {
                      ...v,
                      address: address[keyIndex]
                    }
                  ));
                  localStorage.setItem('success', JSON.stringify(storeData));
                })

              }}>
                <span>立即支付</span>
              </button>
            </div>
          </div>
        </div>
      }

      {
        success && <div className={styles.modal}>
          <div className={styles.bg} onClick={() => {
            if (success) setSuccess(false);
          }}></div>
          <div className={styles['modal-div']}>
            <h3>谢谢购买</h3>
            <ul>
              {
                store && store.map(v => (
                  <li>
                    <img src={v.imageUrl} alt=""/>
                    <span class={styles.span1}>{v.title}</span>
                    <span class={styles.span2}>数量：{v.num}</span>
                  </li>
                ))
              }
            </ul>
          </div>
        </div>
      }
    </div>
  )
}