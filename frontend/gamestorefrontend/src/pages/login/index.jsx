import { useState, useEffect } from 'react';
import axios from 'axios';
import { history } from 'umi';
import { assetAPI } from '../../utils/index';

import bg1 from '../../assets/bg1.png';
import Header from '../../components/header';

import styles from './index.less'

export default function LoginPage() {

  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [code, setCode] = useState('');
  const [imgCode, setImgCode] = useState(assetAPI + '/kaptcha/kaptcha-image');
  const [tips, setTips] = useState('');

  const handleLogin = () => {

    if (!username) {
      setTips('账号不能为空');
      return;
    }

    if (!password) {
      setTips('密码不能为空');
      return;
    }

    if (!code) {
      setTips('验证码不能为空');
      return;
    }

    setTips('');

    axios.get('/api/user/login', {
      params: {
        username: username,
        password: password,
        code: code,
      }
    }).then(res => {
      if (res.data.state === 200) {
        alert("登录成功！");
        sessionStorage.setItem('token', JSON.stringify(res.data.data));
        history.replace('/');
        sessionStorage.setItem('isLogin', true);
      } else {
        setTips(res.data.message);
      }
    })
  }

  const handleRegister = () => {
    history.push('/register');
  }

  const handleChangeUser = (e) => {
    setUserName(e.target.value)
  }

  const handleChangePwd = (e) => {
    setPassword(e.target.value);
  }

  const handleCode = (e) => {
    setCode(e.target.value);
  }

  const handleCodeChange = () => {
    setImgCode(assetAPI + "/kaptcha/kaptcha-image?time="+ new Date());
  }

  return (
    <div>
      { Header() }
      <div>
        <div className={styles.content}>
          <img src={bg1} alt=""/>
          <div className={styles.modal}>
            <h4>用户登录</h4>
            { tips && <span className={styles.tips}>{tips}</span> }
            <div className={styles['input-box']}>
              <span>账号</span>
              <input type="text" value={username} onChange={(e) => handleChangeUser(e)} />
            </div>
            <div className={styles['input-box']}>
              <span>密码</span>
              <input type="text" value={password} onChange={(e) => handleChangePwd(e)} />
            </div>
            <div className={styles['input-box'], styles['single']}>
              <span>验证码</span>
              <input type="text" value={code} onChange={(e) => handleCode(e)} />
              <img src={imgCode} alt="" onClick={() => handleCodeChange()} />
            </div>
            <button onClick={() => handleLogin()}>登录</button>
            <span className={styles['desc']}>还没有账号，<a onClick={(e) => handleRegister(e)}>去注册</a></span>
          </div>
        </div>
      </div>
    </div>
  );
}
