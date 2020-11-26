import React from 'react';
import { Text, View , Image, Button, TouchableHighlight} from 'react-native';
import { MaterialIcons,Ionicons,EvilIcons,FontAwesome } from '@expo/vector-icons'
import SubHeader from "./SubHeader";

const InvoiceScreen = (props) => {
    
    elemtJson = {
        'Loại xe' : 'Yamaha icat v4',
        'Mã vạch' : '135 234 2324' ,
        'Thời điểm thuê' : '1 giờ 30 phút',
        'Thời điểm trả' : '10.000 VND',
        'Thời gian thuê': '300.000 VND',
        'Số tiền phải trả' : '150.000 VND',
        'Số tiền đã cọc' : '300.000 VND',
        'Số tiền đã nhận lại' : '150.000VND'
    }
  return (
    <View style= {styles.container}>
        {/* <HeaderCompo style= {styles.header}/> */}
        <SubHeader title="Chi tiết giao dịch"/>
        <View style = {styles.textHeader}>
                  <Text style= {{color : '#636262' , fontWeight : '200'}}>
                    Thanh toán thành công ! cảm ơn bạn đã sử dụng dịch vụ của chúng tôi
                  </Text>
              </View>
        <View style = {styles.textwap}>
          <View style = {styles.customMargin}>
            <Text style ={ {marginTop : -10, fontWeight:'200', textAlign : 'center' ,color : '#636262'}}>Chi tiết giao dịch</Text>
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
                onPress={() => props.navigation.navigate('Home')}
                underlayColor='#ffff'>
                <Text style={[styles.submitText]}>Quay về trang chủ</Text>
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
    textHeader : {
        marginTop : '5%',
        marginLeft : '5%',
        marginRight : '5%',
        marginBottom : '5%',
        textAlign : 'center',
        
        // width : '100%',
        // height : '10%',
        // backgroundColor : '#F6F4F4'
      },
  keyValues : {
    flex : 1 ,
    flexDirection : 'row',
    // backgroundColor : '#BDBDBD',
    marginRight : '6%'
  },
  buttonWap : {
    height : '10%',
    width : '100%',
    marginTop : "1%",
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
    height: '65%',
    width : '85%',
    marginLeft: '8%',
    marginTop: '0%',
    borderRadius:15,
    borderWidth: 1,
    borderColor: '#fff'
  },
  elementBd : {
    height: '11%',
    width : '100%',
    // backgroundColor : '#BDBDBD',
  }, 
  customMargin :{
    height: '3%',
    width : '100%'
  },
  sepaLine :{
    height: 1,
    width : '70%',
    backgroundColor :'#F6F4F4',
    marginTop : 10,
    marginLeft: '15%',
    // marginRight: 10
  },
  detailText:{
    color:'#636262',
    // textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop:10,
    marginLeft: 20,
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
customHeadText:{
    color:'#636262',
    // textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop:20,
    marginLeft: 30,
    textDecorationLine: 'underline',
    fontWeight: 'bold'
    // flex : 1 ,

},
customDetailText:{
    color:'#636262',
    textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop:10,
    marginLeft: 10,
    // textDecorationLine: 'underline',
    fontWeight: 'bold',
    fontSize : 20,
    // flex : 1 ,

},
 

};
export default InvoiceScreen 