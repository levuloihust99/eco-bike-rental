import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight } from 'react-native';
import HeaderCompo from './Header';
import PackingLotElement from './PackingLotElement';

export default class HomeScreen extends React.Component {
    prop = [{
            id : 1,
            name : 'Vườn Hướng Dươnggggggggg',
            address : 'Số 4/6, Đường Nguyễn Trãiiiiiii'
        }, {
            id : 2,
            name : 'Vườn Hướng Dươnggggggggg',
            address : 'Số 4/6, Đường Nguyễn Trãiiiiiii'
        },
        {
            id : 3,
            name : 'Vườn Hướng Dươnggggggggg',
            address : 'Số 4/6, Đường Nguyễn Trãiiiiiii'
        },
        {
            id :4 ,
            name : 'Vườn Hướng Dươnggggggggg',
            address : 'Số 4/6, Đường Nguyễn Trãiiiiiii'
        },
        {
            id :5,
            name : 'Vườn Hướng Dươnggggggggg',
            address : 'Số 4/6, Đường Nguyễn Trãiiiiiii'
        }    
    ]
       
    state = {
        myState : 'renting'
    }
    updateState = (newState) => {
        this.state = {myState : newState}
      }
    buttonCondition = () => {
        if (this.state.myState === 'renting') {
          return (
            <TouchableHighlight
                style={styles.submit}
                onPress={() => this}
                underlayColor='#ffff'>
                <Text style={[styles.submitText]}>Thuê xe</Text>
            </TouchableHighlight>
          ) ;
        } else if (this.state.myState === 'rented'){
          return (
              <TouchableHighlight
                  style={[styles.submit , {marginLeft: '25%', width : '50%'}]}
                  onPress={() => this}
                  underlayColor='#FA2626'>
                  <Text style={[styles.submitText]}>XEM XE ĐANG THUÊ</Text>
              </TouchableHighlight>
          );
        } else {
          return (
            <TouchableHighlight
                style={styles.submit}
                onPress={() => this}
                underlayColor='#ffff'>
                <Text style={[styles.submitText]}> {this.state.myState}</Text>
            </TouchableHighlight>
          );
        };
      }
      render(){
        return (
            <View style = {styles.container}>
              <HeaderCompo style= {styles.header}/>
              <Image style= {styles.body} source={require('./image/4871.jpg')}/>
               {  this.buttonCondition()}
              <ScrollView>
                {/* <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/>
                <PackingLotElement name= {this.prop.name}  address = {this.prop.address}/> */}
                {this.prop.map((y)=> {
                    return (
                        <PackingLotElement key = {y['id']} name = {y['name']} address = {y['address']}> </PackingLotElement>
                    )
                })}
              </ScrollView>
            </View>
          );
      }
    

}
const styles = StyleSheet.create({
  container: {
    width : '100%',
    height : '100%',
    flexDirection : 'column',
    // alignItems : 'center'
    // flex :1 
  },
  header :{
    width : '20%',
    height : '30%',
    
  },
  body :{
    width : '100%',
    height : '30%',
    
  },
  submit:{
    // flex:1,
    height: '7%',
    width : '40%',
    
    // marginRight:40,
    marginLeft: '30%',
    marginTop:-20,
    // paddingTop:10,
    // paddingBottom:10,
    backgroundColor:'#08BD5F',
    borderRadius:6,
    borderWidth: 1,
    borderColor: '#fff'
  },
  submitText:{
      color:'#fff',
      textAlign:'center',
      marginTop:10,
  }

});