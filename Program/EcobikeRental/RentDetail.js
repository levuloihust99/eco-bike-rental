import React from 'react';
import { Text, View , Image, Button, TouchableHighlight} from 'react-native';
import { MaterialIcons,Ionicons,EvilIcons,FontAwesome } from '@expo/vector-icons'
import HeaderCompo from './Header';

element_text = [
  'Loại xe',
  'Giá thuê',
  'Thời gian đi tối đa',
  'Mã vạch',
  'Sử dụng lần cuối',
]
elemtJson = {
   'Loại xe' : 'Yamaha icat 44444',
   'Giá thuê' : '150.000 VND' ,
   'Thời gian đi tối đa' : '1h20m',
    'Mã vạch' : '10293948502',
    'Sử dụng cuối' : '21/10/2020',
    'Giá cọc' : '150.000 VND'
}
const RentDetail = (props) => {
  return (
    <View style= {styles.container}>
        <HeaderCompo style= {styles.header}/>
        <View style = {styles.textwap}>
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
                <Text style={[styles.submitText]}>Tiếp tục</Text>
            </TouchableHighlight>
        </View>
       
        
       
    </View>
    
  )
}
const Element = (props) => {
  return (
    <View style= {styles.elementBd}>
      <View style = {styles.keyValues}>
        <Text style = {styles.detailText}>{props.name} :</Text>
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
    height: '70%',
    width : '85%',
    marginLeft: '8%',
    marginTop: '8%',
    borderRadius:15,
    borderWidth: 1,
    borderColor: '#fff'
  },
  elementBd : {
    height: '16%',
    width : '100%',
    // backgroundColor : '#BDBDBD',
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
export default RentDetail 