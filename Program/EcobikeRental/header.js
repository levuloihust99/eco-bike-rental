import React from 'react';
import { Text, View , Image} from 'react-native';
import { MaterialIcons,Ionicons,EvilIcons } from '@expo/vector-icons'

const HeaderCompo = (props) => {
  return (
    // <Image source={require('./image/icon.png')} />
    <View style= {styles.container}>
        <View style = {styles.iconStyle}>
           <MaterialIcons name="menu" size={40} color='#BDBDBD'  />
        </View>
        <View style = {styles.iconAvatar}>
          
        </View>
        <View></View>
        <View style = {styles.iconStyle}>
            <EvilIcons name="search" size={40} color='#BDBDBD' />
        </View>
    </View>
    
  )
    // <View style>
          
    //   {/* <Text style= {styles.iconStyle} onPress = {props.updateState}>
    //      {props.myState}
    //   </Text> */}
    // </View>
  // )
}
const styles = {
  container : {
    backgroundColor: '#FFFFFF',
    // flex : 1 ,
    flexDirection : 'row'
  },
  bgHeader: {
    backgroundColor: '#0288D1',
    justifyContent:'center',
    alignItems:'center',
  },
  headerStyle: {
    fontSize: 25,
    textAlign: 'center',
    margin: 10,
    color: '#fff',
  },
  iconStyle : {
    marginTop: 30,
    marginLeft:10,
    textAlign: 'center',
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 10,
    height: '100%',
    width : '30%',
  },
  iconAvatar : {
    marginTop: 30,
    marginLeft:10,
    textAlign: 'center',
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 10,
    height: '100%',
    width : '50%',
  
  },

};
export default HeaderCompo 