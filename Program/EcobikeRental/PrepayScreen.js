import React from 'react';
import { Text, View , Image, Button, TouchableHighlight} from 'react-native';
import { MaterialIcons,Ionicons,EvilIcons,FontAwesome } from '@expo/vector-icons'
import HeaderCompo from './Header';

const PrepayScreen = (props) => {
  elemtJson = {
    'Tên chủ thẻ' : 'Phạm Minh Khiêm',
    'Số tài khoản' : '135 234 2324' ,
    'Số tiền' : '350.000 VND'
 }
  return (
    <View style= {styles.container}>
        <HeaderCompo style= {styles.header}/>
        <View style = {styles.textwap}>
          <View style = {styles.customMargin}>
            <Text style ={ {marginTop : -10, textAlign : 'center' ,color : '#636262'}}> Thông tin thanh toán</Text>
          </View>
            {
              Object.keys(elemtJson).map((key,vlue) => {
                return (
                  <Element key = {key} name = {key} detail = {elemtJson[key]}></Element>
                )
                })
            }
        </View>
        <View style = {styles.buttonWap}>
            <TouchableHighlight
               style={styles.submit}
                onPress={() => this}
                underlayColor='#ffff'>
                <Text style={[styles.submitText]}>Thanh toán</Text>
            </TouchableHighlight>
        </View>
       
        
       
    </View>
    
  )
}
const Element = (props) => {
  return (
    <View style= {styles.elementBd}>
      <View style = {styles.keyValues}>
        <Text style = {[styles.detailText,{fontSize : 13}]}>{props.name} :</Text>
        <Text style = {[styles.detailText,{marginLeft :10, fontWeight: '500', fontSize : 15}]}>{props.detail}</Text>
      </View>
      <View style= {styles.sepaLine}></View>
    </View>
  )
}
const styles = {
  keyValues : {
    flex : 1 ,
    flexDirection : 'row',
    // backgroundColor : '#BDBDBD',
    marginRight : '6%'
  },
  buttonWap : {
    height : '10%',
    width : '100%',
    marginTop : "10%",
    // backgroundColor : '#FFFEFE',$
  }
  ,
  container : {
    width : '100%',
    height : '100%',
    backgroundColor: '#F6F4F4',
    flex : 1 ,
    borderBottomWidth: 0.5,
    borderColor: '#BDBDBD',
    paddingBottom:12,
    
  }, 
  textwap : {
    backgroundColor : '#FFFEFE',
    height: '50%',
    width : '85%',
    marginLeft: '8%',
    marginTop: '15%',
    borderRadius:15,
    borderWidth: 1,
    borderColor: '#fff'
  },
  elementBd : {
    height: '20%',
    width : '100%',
    // backgroundColor : '#BDBDBD',
  }, 
  customMargin :{
    height: 30,
    width : '100%'
  },
  sepaLine :{
    height: 1,
    width : '70%',
    backgroundColor :'#F6F4F4',
    marginTop : 10,
    marginLeft: '15%',
    marginRignt: 10
  },
  detailText:{
    color:'#636262',
    // textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop:20,
    marginLeft: 30,
    // flex : 1 ,

},
submit:{
  marginRight:40,
  marginLeft:40,
  marginTop:10,
  paddingTop:7,
  paddingBottom:10,
  backgroundColor:'#08BD5F',
  borderRadius:30,
  borderWidth: 1,
  borderColor: '#fff'
},
submitText:{
    color:'#fff',
    textAlign:'center',
},
 

};
export default PrepayScreen 