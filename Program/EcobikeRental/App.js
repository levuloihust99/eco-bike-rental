import React, {useEffect} from 'react';
import HomeScreen from './HomeScreen';
import PrepayScreen from './PrepayScreen';
import RentDetail from './RentDetail';
import RentingDetailScreen from './RentingDetailScreen';
export default class App extends React.Component  {
  render(){
    return (
      // Màn hình chính
        // <HomeScreen></HomeScreen>
      // Thông tin thuê xe
        // <RentDetail></RentDetail>
      // Thanh toán cọc
        // <PrepayScreen></PrepayScreen>
      // Thông tin xe đang được thuê
      <RentingDetailScreen></RentingDetailScreen>
    )
  }
}