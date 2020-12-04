import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight } from 'react-native';
import HeaderCompo from './Header';
import SubHeader from './SubHeader';
import PackingLotElement from './PackingLotElement';
import { MaterialIcons, Ionicons, EvilIcons, FontAwesome } from '@expo/vector-icons'
import { useNavigation } from '@react-navigation/native';
import { baseURL } from './config'
import { storeParkingLotID } from './actions'
import { connect } from 'react-redux'

const defaultProp = [{
  id: 1,
  name: 'Vườn Hướng Dương',
  address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
}, {
  id: 2,
  name: 'Vườn Hướng Dương',
  address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
},
{
  id: 3,
  name: 'Vườn Hướng Dương',
  address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
},
{
  id: 4,
  name: 'Vườn Hướng Dương',
  address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
},
{
  id: 5,
  name: 'Vườn Hướng Dươngggggggg ',
  address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
}
]

class ReturnPackingLotScreen extends React.Component {

  constructor(props){
    super(props)
    this.state = {
      data: defaultProp
    }
  }

  UNSAFE_componentWillMount() {
    fetch(baseURL + 'listParkingLot',
      {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json'
        }
      }
    ).then((response) => response.json())
      .then((json) => {
        this.setState({
          data: json
        });
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {

      });
  }

  render() {
    return (
      <View style={styles.container}>
        {/* <HeaderCompo style= {styles.header}/> */}
        <SubHeader title="Trả xe" />
        <View style={styles.textHeader}>
          <Text>
            Hãy chọn một điểm dừng gần bạn nhất để trả xe
                  </Text>
        </View>
        <View style={styles.body}>
          <ScrollView>
            {this.state.data.map((y) => {
              return (
                <ItemElement key={y['id']} name={y['name']} address={y['address']} parkingLotID={y['id']} parentProps={this.props}> </ItemElement>
              )
            })}
          </ScrollView>
        </View>

      </View>
    );
  }


}
const ItemElement = (props) => {
  const navigation = useNavigation();
  return (
    <View style={styles.elementStyle}>

      <FontAwesome style={styles.map_icon} name="map-marker" size={20} color='#08BD5F' />
      <View style={styles.first_element}>
        <View style={[styles.textBox, { marginTop: '7%' }]}>
          <Text style={{ fontWeight: 'bold', fontSize: 14 }} >{props.name}</Text>
        </View>
        <View style={[styles.textBox, { marginBottom: '7%' }]}>
          <Text style={{ fontSize: 12 }}>{props.address}</Text>
        </View>

      </View>
      <TouchableHighlight
        style={styles.submit}
        onPress={() => {
          props.parentProps.storeParkingLotID(props.parkingLotID)
          navigation.navigate('ReturnBike')}}
        underlayColor='#FA2626'>
        <Text style={[styles.submitText]}>Chọn</Text>
      </TouchableHighlight>


    </View>

  )
}
const styles = StyleSheet.create({
  textHeader: {
    marginTop: '5%',
    marginLeft: '5%',
    marginRight: '5%',
    marginBottom: '5%',
    textAlign: 'center',
    // width : '100%',
    // height : '10%',
    // backgroundColor : '#F6F4F4'
  },
  map_icon: {
    marginTop: 20,
    marginLeft: 10,
    textAlign: 'center',
    height: '100%',
    width: '3.5%',
  },
  first_element: {
    marginTop: '1%',
    marginLeft: 10,
    height: '100%',
    width: '70%',
  },
  submit: {
    height: '45%',
    width: '20%',
    marginRight: 0,
    marginLeft: -10,
    marginTop: '6%',
    backgroundColor: '#08BD5F',
    borderRadius: 30,
    borderWidth: 1,
    borderColor: '#fff'
  },
  submitText: {
    color: '#fff',
    textAlign: 'center',
    // marginBottom : '5%'
  },
  elementStyle: {
    flex: 1,
    marginTop: '2%',
    marginLeft: '5%',
    marginRight: '5%',
    width: '90%',
    height: '20%',
    backgroundColor: '#FFFFFF',
    borderRadius: 20,
    flexDirection: 'row'
  },
  textBox: {
    // width : '80%',
    marginLeft: '3%',
    marginTop: '3%'
  },
  textHeader: {
    marginTop: '5%',
    marginLeft: '5%',
    marginRight: '5%',
    marginBottom: '5%',
    textAlign: 'center',
    // width : '100%',
    // height : '10%',
    // backgroundColor : '#F6F4F4'
  },
  container: {
    width: '100%',
    height: '100%',
    flexDirection: 'column',
    backgroundColor: '#F6F4F4'
    // alignItems : 'center'
    // flex :1 
  },
  header: {
    width: '20%',
    height: '30%',

  },
  body: {
    width: '100%',
    height: '100%',
    backgroundColor: '#F6F4F4'

  },
  submitText: {
    color: '#fff',
    textAlign: 'center',
    marginTop: 10,
  }

});

const mapDispatchToProps = (dispatch) => {
  return {
    storeParkingLotID: (value) => dispatch(storeParkingLotID(value))
  }
}

export default connect(null, mapDispatchToProps)(ReturnPackingLotScreen)