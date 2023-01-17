import { useState, useEffect } from 'react';
import Slider from "react-slick";
import axios from 'axios';
import { history } from 'umi';
import { assetAPI } from '../../utils/index';

import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";
import Header from '../../components/header';
import styles from './index.less';

import banner from '../../assets/index_banner2.png';

export default function IndexPage() {

  const [list, setList] = useState([]);

  useEffect(() => {
    axios.get('/api/product/hot_list').then(res => {
      console.log('====>');
      console.log(res);

      const newList = res.data.data.map(v => {
        return {
          ...v,
          // imgUrl: assetAPI + '' + v.image + 'collect.png',
        }
      });
      localStorage.setItem('hotList', JSON.stringify(newList));
      setList(newList);
    })
  }, [])

  const handleSearch = (e) => {
    const storeList = JSON.parse(localStorage.getItem('hotList'));
    const filterList = storeList.filter(v => v.title.includes(e));
    setList(filterList);
  }

  const handleClick = (e) => {
    console.log(e);
    history.push('/detail', {
      msg: e,
    });
  }

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1
  };

  return (
    <div>
      <Header handleSearch={(e) => handleSearch(e)} />
      <Slider {...settings}>
        <div className={styles.banner}>
          <img src={banner} alt=""/>
        </div>
        <div className={styles.banner}>
          <img src={banner} alt=""/>
        </div>
        <div className={styles.banner}>
          <img src={banner} alt=""/>
        </div>
      </Slider>
      <div className={styles.list}>
        {
          list && list.length && list.map(v => (<div key={v.id} className={styles.item} onClick={() => handleClick(v)}>
            <div className={styles.inner}>
              <img src={v.imageUrl} alt=""/>
              <h6>{v.title}</h6>
              <h6>${v.price}</h6>
            </div>
          </div>))
        }
      </div>
      <footer>
        <h3>DNSC.CN电脑商城</h3>
      </footer>
    </div>
  );
}
