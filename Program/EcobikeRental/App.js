import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import HeaderCompo from './header';
export default class App extends React.Component  {
  state = {
    myState : 'abcdedkmvkmvkdmksjjdnjdcmvddssdf'
  }
  updateState = () => {
    this.setState({ myState : 'Updated'})
  }
  render(){
    return (
      <View style = {styles.container}>
        <HeaderCompo myState = {this.state.myState} updateState = {this.updateState}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    width : '100%',
    height : '12%',
    backgroundColor: '#08BD5F',
    // flex :1 
  },
});
