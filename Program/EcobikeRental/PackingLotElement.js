import React from 'react';
import { Text, View , Image, Button, TouchableHighlight} from 'react-native';
import { MaterialIcons,Ionicons,EvilIcons,FontAwesome } from '@expo/vector-icons'

const PackingLotElement = (props) => {
  return (
    <View style= {styles.container}>

        <FontAwesome style= {styles.map_icon} name="map-marker" size={20} color='#08BD5F' /> 
        <View style= {styles.first_element}>
          <View style = {styles.textBox}>
             <Text  style= { {fontWeight: 'bold',   fontSize: 14}} >{props.name}</Text>
          </View>
          <View  style= {styles.textBox}>
            <Text style= { {fontSize: 12}}>{props.address}</Text>
          </View>
           
        </View>
        <TouchableHighlight
            style={styles.submit}
            onPress={() => this}
            underlayColor='#ffff'>
            <Text style={[styles.submitText]}>Xem</Text>
        </TouchableHighlight>
        
       
    </View>
    
  )
}
const styles = {
  container : {
    height: 80,
    width : '100%',
    backgroundColor: '#FFFFFF',
    flex : 1 ,
    flexDirection : 'row',
    borderBottomWidth: 0.5,
    borderColor: '#BDBDBD',
    paddingBottom:12
  },
  map_icon : {
    marginTop: 20,
    marginLeft:10,
    textAlign: 'center',
    // color: 'blue',
    fontWeight: 'bold',
    // fontSize: 10,
    height: '100%',
    width : '5%',
  },
  first_element : {
    marginTop: 20,
    marginLeft:10,
    textAlign: 'center',
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 10,
    height: '100%',
    width : '50%',
  },
  submit:{
    height: '60%',
    width : '25%',
    marginRight:40,
    marginLeft:40,
    marginTop:20,
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
  textBox :{
    height : '30%',
    width  : '100%',
    overflow: 'hidden',
  }

};
export default PackingLotElement 