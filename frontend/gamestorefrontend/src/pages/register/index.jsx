import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { history } from 'umi';
import { assetAPI } from '../../utils/index';

import bg1 from '../../assets/bg1.png';
import Header from '../../components/header';

import styles from './index.less'

export default function LoginPage() {

  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [reword, setReWord] = useState('');
  const [code, setCode] = useState('');
  const [imgCode, setImgCode] = useState(assetAPI + '/kaptcha/kaptcha-image');
  const [tips, setTips] = useState('');

  const handleLogin = () => {

    if (!username) {
      setTips('用户名不能为空');
      return;
    }

    if (!(/^\w{5,12}$/.test(username))) {
      setTips('用户名必须是5-12位的字母和数字');
      return;
    }

    if (!password) {
      setTips('密码不能为空');
      return;
    }

    if (!(/^\w{5,12}$/.test(password))) {
      setTips('密码必须是5-12位的字母和数字');
      return;
    }

    if (!reword) {
      setTips('确认密码不能为空');
      return;
    }

    if (password !== reword) {
      setTips('两次输入的密码不一样');
      return;
    }

    if (!code) {
      setTips('验证码不能为空');
      return;
    }


    let formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
    formData.append('reword', reword);
    formData.append('code', code);

    axios.post('/api/user/reg', formData).then(res => {

      if (res.data.state === 200) {
        alert("注册成功！");
        history.replace('/login');
      } else {
        setTips(res.data.message);
      }
    })
  }

  const handleReturn = () => {
    history.back();
  }

  const handleChangeUser = (e) => {
    setUserName(e.target.value)
  }

  const handleChangePwd = (e) => {
    setPassword(e.target.value);
  }

  const handleChangeRewd = (e) => {
    setReWord(e.target.value);
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
            <h4>用户注册</h4>
            { tips && <span className={styles.tips}>{tips}</span> }
            <div className={styles['input-box']}>
              <span>账号</span>
              <input type="text" value={username} onChange={(e) => handleChangeUser(e)} />
            </div>
            <div className={styles['input-box']}>
              <span>密码</span>
              <input type="text" value={password} onChange={(e) => handleChangePwd(e)} />
            </div>
            <div className={styles['input-box']}>
              <span>确认密码</span>
              <input type="text" value={reword} onChange={(e) => handleChangeRewd(e)} />
            </div>
            <div className={styles['input-box'], styles['single']}>
              <span>验证码</span>
              <input type="text" value={code} onChange={(e) => handleCode(e)} />
              <img src={imgCode} alt="" onClick={() => handleCodeChange()} />
            </div>
            <button onClick={() => handleLogin()}>注册</button>
            <span className={styles['desc']}>已有账号，<a onClick={() => handleReturn()}>返回登录</a></span>
          </div>
        </div>
      </div>
    </div>
  );
}
