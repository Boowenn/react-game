import { useState } from 'react';
import message from '../../../components/message';
import axios from 'axios';
import { history } from 'umi';

import styles from './index.less'

export default function AdminPage() {

  const [inputUser, setInputUser] = useState('');
  const [inptPwd, setInptPwd] = useState('');


  return (
    <div className={styles.admin}>
      <h3> 商城系统登录 </h3>
      <div className={styles.box}>
        <span></span>
        <input type="text" value={inputUser} onChange={(e) => {
          setInputUser(e.target.value);
        }} placeholder="账号"/>
      </div>
      <div className={styles.box}>
        <span></span>
        <input type="text" value={inptPwd} onChange={(e) => {
          setInptPwd(e.target.value);
        }} placeholder="密码"/>
      </div>
      <button onClick={() => {
        if (!inputUser) {
          message.error('请输入账号');
          return;
        }

        if (!inptPwd) {
          message.error('请输入密码');
          return;
        }

        axios.get('/api/admin/login', {
          params: {
            username: inputUser,
            password: inptPwd,
          }
        }).then(res => {
          console.log('====>');
          console.log(res);
          if (res.data.state === 200) {
            message.success('登录成功');
            history.replace('/admin/home');
          } else {
            message.error(res.data.message || '');
          }
        })

      }}>
        <span> 登录 </span>
      </button>
    </div>
  )
}
