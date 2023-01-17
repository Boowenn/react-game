import { useState, useEffect } from 'react';
import Header from '../../components/header';
import axios from 'axios';
import { history } from 'umi';

import styles from './index.less';

export default function DetailPage(props) {

  const [msg, setMsg] = useState(null);
  const [normalNum, setNormalNum] = useState(1);

  useEffect(() => {
    setMsg(history.location.state.msg || null);
  }, []) 

  const handleAdd = () => {

    let formData = new FormData();
    formData.append('pid', msg.id);
    formData.append('price', msg.price);
    formData.append('num', normalNum);

    axios.post('/api/cart/add_cart', formData).then(res => {

      console.log(res);

      if (res.data.state === 200) {
        alert("加入购物车成功");
        history.replace('/shop');
      } else {
        alert("加入购物车失败");
      }
    })
  }

  const handleAddNum = () => {
    const newNum = normalNum + 1;
    setNormalNum(newNum);
  }

  const handleRelease = () => {
    if (normalNum === 1) return;
    const newNum = normalNum - 1;
    setNormalNum(newNum);
  }


  return (
    <div>
      { Header() }

      <div className={styles.inner}>
        <img src={msg && msg.imageUrl} alt=""/>
        <div>
          售价: ¥{ msg && msg.price }
        </div>
        数量: 
        <div className={styles.inputNum}>
          <button onClick={() => handleRelease()}>-</button>
          <p>{ normalNum }</p>
          <button onClick={() => handleAddNum()}>+</button>
        </div>
      </div>

      <div>
        <button onClick={() => handleAdd()}>加入购物车</button>
      </div>
    </div>
  )
}
