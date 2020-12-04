import React, { useEffect } from 'react';
import HomeScreen from './HomeScreen';
import PrepayScreen from './PrepayScreen';
import RentDetail from './RentDetail';
import RentBikeScreen from './RentBikeScreen';
import RentingDetailScreen from './RentingDetailScreen';
import { View, Text, Image } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import ReturnDetail from './ReturnDetail';
import PackingLotElement from './PackingLotElement';
import ReturnPackingLotScreen from './ReturnPackingLotScreen';
import ReturnBike from './ReturnBike';
import InvoiceScreen from './InvoiceScreen';
import PaymentInfo from './PaymentInfo';
import store from './store';
import storeUpFrontPrice from './actions'
import { Provider, connect } from 'react-redux'

const Stack = createStackNavigator();

// Create store
// const store = Redux.createStore(rootReducer, initialState)

class App extends React.Component {
    render() {
        return (
            <NavigationContainer>
                <Stack.Navigator
                    screenOptions={{
                        headerShown: false
                    }}
                >
                    <Stack.Screen name='Home' component={HomeScreen}
                    />
                    <Stack.Screen name='RentBike' component={RentBikeScreen} />
                    <Stack.Screen name='PrepayScreen' component={PrepayScreen}
                    />
                    <Stack.Screen name='RentDetail' component={RentDetail} />
                    <Stack.Screen name='RentingDetailScreen' component={RentingDetailScreen} />
                    <Stack.Screen name='ReturnBike' component={ReturnBike} />
                    <Stack.Screen name='ReturnDetail' component={ReturnDetail} />
                    <Stack.Screen name='ReturnPackingLotScreen' component={ReturnPackingLotScreen} />
                    <Stack.Screen name='InvoiceScreen' component={InvoiceScreen} />
                    <Stack.Screen name='PaymentInfo' component={PaymentInfo} />
                </Stack.Navigator>
            </NavigationContainer>
        )
    }
}

const RootComponent = connect(null, mapDispatchToProps)(App);

export default class AppWrapper extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <RootComponent />
            </Provider>
        )
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        storeUpFrontPrice: (price) => dispatch(storeUpFrontPrice(price))
    }
}