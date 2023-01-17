import { useState, useEffect } from 'react';
import { history } from 'umi';
import Popup from 'react-popup';

import '../../popup.css';
import styles from './index.less';

const Header = (props) => {

  const [value, setValue] = useState('');
  const [userName, setUserName] = useState('');
  const [init, setInit] = useState(true);

  useEffect(() => {
    const token = sessionStorage.getItem('token') ? JSON.parse(sessionStorage.getItem('token')) : null;
    setUserName(token ? token.username : '');
  }, [init])

  const handleChange = (e) => {
    setValue(e.target.value);
  }

  const handleSearch = (e) => {
    props && props.handleSearch(value);
  }

  const linkShop = () => {
    history.push('/shop');
  }

  const linkLogin = () => {
    history.push('/login');
  }

  const handleHome = () => {
    history.replace('/');
  }

  const handleLogout = () => {

    let mySpecialPopup = Popup.register({
      title: '确定要退出吗?',
      content: '确定要退出吗?',
      buttons: {
        left: ['取消'],
        right: [{
          text: '确定',
          key: '⌘+s',
          className: 'success',
          action: function () {
            sessionStorage.removeItem('token');
            setInit(!init);
            Popup.close();
          }
        }]
      }
    });
    Popup.queue(mySpecialPopup);
  }

  const handleOrder = () => {
    history.push('/order');
  }


  return (
    <header className={styles.header}>
      <div className={styles['header-wrap']}>
        <h3 onClick={() => handleHome()}>游戏商城</h3>
        <div className={styles['input-box']}>
          <input type="text" value={value} onChange={(e) => handleChange(e)} placeholder="搜索商品"/>
        </div>
        <button onClick={(e) => handleSearch(e)}>搜索</button>
      </div>
      <div className={styles['header-right']}>
        <ul>
          <li onClick={() => linkShop()}>
            购物车
          </li>
          {/* <li onClick={() => handleOrder()}>订单</li> */}
          {
            userName ? <li>
              欢迎您： <span className={styles.username}>{ userName }</span>
            </li>: <li onClick={() => linkLogin()}>
              登录
            </li>
          }
          {
            userName && <li onClick={() => {  history.push('/manage'); }}>
              管理
            </li>
          }
          {
            userName && <li onClick={() => handleLogout()}>
              退出登录
            </li>
          }
        </ul>
        <Popup />
        {/* <span>欢迎访问：游戏商城</span> */}
      </div>
    </header>
  )
}

export default Header;
