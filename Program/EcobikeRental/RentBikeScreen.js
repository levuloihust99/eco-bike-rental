import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight, TextInput } from 'react-native';
import { baseURL } from './config';
import SubHeader from './SubHeader';
import {connect} from 'react-redux'
import { storeBikeID } from './actions';


function RentBikeScreen(props) {
    const [text, setText] = React.useState('');
    const [respStatus, setRespStatus] = React.useState('initial');

    const validateInput = () => {
        console.log("Before sending request: " + text);
        if (!text) {
            return
        }
        fetch(baseURL + "getBikeByBarcode",
            {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    barcode: text
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
        if (respStatus?.error){
            alert(respStatus.error);
        }
        else if (respStatus !== 'initial') {
            props.storeBikeID(text)
            props.navigation.navigate('RentDetail', {respStatus: respStatus})
        }
    }, [respStatus])

    return (
        <View style={{ flex: 1 }}>
            <SubHeader title="Thuê xe" />
            <View style={{ marginTop: 40, marginLeft: 20, marginRight: 20 }}>
                <Text style={{ fontSize: 24, color: '#635F5F' }}>Nhập mã vạch</Text>
                <TextInput style={{
                    height: 40, borderBottomWidth: 0.5,
                    borderColor: '#BDBDBD', marginTop: 20, fontSize: 18,
                }}
                    onChangeText={text => setText(text)}
                // value={text}
                />
                <TouchableHighlight
                    style={styles.submit}
                    onPress={validateInput}
                    underlayColor='#FA2626'>
                    <Text style={styles.submitText}>THUÊ</Text>
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

const mapDispatchToProps = (dispatch) => {
    return {
        storeBikeID: (value) => dispatch(storeBikeID(value))
    }
}

export default connect(null, mapDispatchToProps)(RentBikeScreen)