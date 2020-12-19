import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight, TextInput } from 'react-native';
import SubHeader from './SubHeader';
import {baseURL} from './config';
import { useStore } from 'react-redux'

export default function ReturnBike({navigation}) {
    const store = useStore()
    const [text, setText] = React.useState('');
    const [respStatus, setRespStatus] = React.useState('initial');

    const validateInput = () => {
        // console.log("Before sending request: " + text);
        if (!text){
            return
        }
        fetch(baseURL + "returnDetail",
            {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    barcode: text,
                    cardID: store.getState().cardID,
                    // parkingLotID: store.getState().parkingLotID
                })
            }
        ).then((response) => response.json())
            .then((json) => {
                // console.log(json)
                setRespStatus(json)
            })
            .catch((error) => {
                console.error(error);
            })
            .finally(() => {
                // console.log(respStatus)
            })
    }

    React.useEffect(() => {
        console.log(respStatus)
        if (respStatus?.Error){
            alert(respStatus.Error);
        }
        else if (respStatus !== 'initial') {
            navigation.navigate('ReturnDetail', {status: respStatus})
        }
    }, [respStatus])

    return (
        <View style={{ flex: 1 }}>
            <SubHeader title="Trả xe" />
            <View style={{ marginTop: 40, marginLeft: 20, marginRight: 20 }}>
                <Text style={{ fontSize: 24, color: '#635F5F' }}>Nhập mã vạch</Text>
                <TextInput style={{
                    height: 40, borderBottomWidth: 0.5,
                    borderColor: '#BDBDBD', marginTop: 20, fontSize: 18
                }} 
                onChangeText={text => setText(text)}/>
                <TouchableHighlight
                    style={styles.submit}
                    onPress={validateInput}
                    underlayColor='#FA2626'>
                    <Text style={styles.submitText}>TRẢ XE</Text>
                </TouchableHighlight>
            </View>
        </View>
    );
}

const styles = {
    submit: {
        height: 40,
        marginTop: 20,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#08BD5F',
        borderRadius: 30,
    },
    submitText: {
        color: '#fff',
        textAlign: 'center',
        fontSize: 20
    },
}