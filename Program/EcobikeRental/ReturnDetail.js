import React from 'react';
import { Text, View, Image, Button, TouchableHighlight } from 'react-native';
import { MaterialIcons, Ionicons, EvilIcons, FontAwesome } from '@expo/vector-icons'
import HeaderCompo from './Header';
import SubHeader from './SubHeader';
import {baseURL} from './config'

const ReturnDetail = (props) => {

  const defaultReturnDetailElemtJson = {
    'Loại xe': 'Yamaha icat v4',
    'Mã vạch': '135 234 2324',
    'Thời gian thuê': '1 giờ 30 phút',
    'Giá thành tiền': '10.000 VND',
    'Đã cọc': '300.000 VND',
    'Số tiền nhận lại sau sau trả xe': '150.000 VND'
  }
  const [resp, setResp] = React.useState('initial')
  const returnDetailElemtJson = props.route.params?.status ? props.route.params.status : defaultReturnDetailElemtJson

  const validateInfo = () => {
    fetch(baseURL + "payTransaction",
      {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          bikeID: '8',
          userID: '1',
          parkingLotID: '1'
        })
      }
    ).then((response) => response.json())
      .then((json) => {
        console.log(json)
        setResp(json)
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {
        // console.log(respStatus)
      })
  }

  React.useEffect(() => {
    if (resp !== 'initial'){
      props.navigation.navigate('InvoiceScreen', {data: resp})
    }
  }, [resp])

  return (
    <View style={styles.container}>
      {/* <HeaderCompo style= {styles.header}/> */}
      <SubHeader title="Thông tin trả xe" />
      <View style={styles.textwap}>
        <View style={styles.customMargin}>
          <Text style={{ marginTop: -10, textAlign: 'center', color: '#636262' }}> Thông tin trả xe</Text>
        </View>
        {
          Object.keys(returnDetailElemtJson).map((key, vlue) => {
            if (key === 'Số tiền nhận lại sau sau trả xe') {
              return (
                <View>
                  <Text style={styles.customHeadText} key={key}>{key}</Text>
                  <Text style={styles.customDetailText} key={returnDetailElemtJson[key]}> {returnDetailElemtJson[key]}</Text>
                </View>

              )
            } else
              return (
                <Element key={key} name={key} detail={returnDetailElemtJson[key]}></Element>
              )
          })
        }
      </View>
      <View style={styles.buttonWap}>
        <TouchableHighlight
          style={styles.submit}
          onPress={validateInfo}
          underlayColor='#ffff'>
          <Text style={[styles.submitText]}>Thanh toán</Text>
        </TouchableHighlight>
      </View>



    </View>

  )
}
const Element = (props) => {
  return (
    <View style={styles.elementBd}>
      <View style={styles.keyValues}>
        <Text style={[styles.detailText, { fontSize: 13 }]}>{props.name} :</Text>
        <Text style={[styles.detailText, { marginLeft: 10, fontWeight: '500', fontSize: 15 }]}>{props.detail}</Text>
      </View>
      <View style={styles.sepaLine}></View>
    </View>
  )
}
const styles = {
  keyValues: {
    flex: 1,
    flexDirection: 'row',
    // backgroundColor : '#BDBDBD',
    marginRight: '6%'
  },
  buttonWap: {
    height: '10%',
    width: '100%',
    marginTop: "5%",
    // backgroundColor : '#FFFEFE',$
  }
  ,
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#F6F4F4',
    flex: 1,
    borderBottomWidth: 0.5,
    borderColor: '#BDBDBD',
    paddingBottom: 12,

  },
  textwap: {
    backgroundColor: '#FFFEFE',
    height: '70%',
    width: '85%',
    marginLeft: '8%',
    marginTop: '8%',
    borderRadius: 15,
    borderWidth: 1,
    borderColor: '#fff'
  },
  elementBd: {
    height: '14%',
    width: '100%',
    // backgroundColor : '#BDBDBD',
  },
  customMargin: {
    height: 30,
    width: '100%'
  },
  sepaLine: {
    height: 1,
    width: '70%',
    backgroundColor: '#F6F4F4',
    marginTop: 10,
    marginLeft: '15%',
    marginRignt: 10
  },
  detailText: {
    color: '#636262',
    // textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop: 20,
    marginLeft: 30,
    // flex : 1 ,

  },
  submit: {
    marginRight: 40,
    marginLeft: 40,
    marginTop: 10,
    paddingTop: 7,
    paddingBottom: 10,
    backgroundColor: '#08BD5F',
    borderRadius: 30,
    borderWidth: 1,
    borderColor: '#fff'
  },
  submitText: {
    color: '#fff',
    textAlign: 'center',
  },
  customHeadText: {
    color: '#636262',
    // textAlign:'center',
    // height : '100%',
    // width : '50%',
    marginTop: 20,
    marginLeft: 30,
    textDecorationLine: 'underline',
    fontWeight: 'bold'
    // flex : 1 ,

  },
  customDetailText: {
    color: '#636262',
    textAlign: 'center',
    // height : '100%',
    // width : '50%',
    marginTop: 10,
    marginLeft: 10,
    // textDecorationLine: 'underline',
    fontWeight: 'bold',
    fontSize: 20,
    // flex : 1 ,

  },


};
export default ReturnDetail 