import React, {useEffect} from 'react';
import HomeScreen from './HomeScreen';
import PrepayScreen from './PrepayScreen';
import RentDetail from './RentDetail';
import RentingDetailScreen from './RentingDetailScreen';
import { View, Text, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

export default class App extends React.Component  {
  render(){
    return (
        <NavigationContainer>
            <Stack.Navigator
                screenOptions={{
                    headerShown: false
                }}
            >
                <Stack.Screen name='Home' component={HomeScreen} 
                />
                <Stack.Screen name='PrepayScreen' component={PrepayScreen} 
                />
                <Stack.Screen name='RentDetail' component={RentDetail} />
                <Stack.Screen name='RentingDetailScreen' component={RentingDetailScreen} />
            </Stack.Navigator>
        </NavigationContainer>
      // Màn hình chính
        // <HomeScreen></HomeScreen>
      // Thông tin thuê xe
        // <RentDetail></RentDetail>
      // Thanh toán cọc
        // <PrepayScreen></PrepayScreen>
      // Thông tin xe đang được thuê
    //   <RentingDetailScreen></RentingDetailScreen>
    )
  }
}