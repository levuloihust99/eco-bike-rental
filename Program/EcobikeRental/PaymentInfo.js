import React from 'react';
import { Text, View, Image, Button, TouchableHighlight, TextInput } from 'react-native';
import { MaterialIcons, Ionicons, EvilIcons, FontAwesome } from '@expo/vector-icons'
import SubHeader from './SubHeader';
import {baseURL} from './config.js'

var paymentElemtJson = {
    "Mã thẻ": "118131_group6_2020",
    "Chủ thẻ": "Group 6",
    "CVV": "266",
    "Ngày hết hạn": "1125"
}

var status = {
    cardID: '',
    cardOwner: '',
    cvv: '',
    expireDate: ''
}

var resp = {};

export default PaymentInfo = (props) => {
    const sendInfo = () => {
        console.log(baseURL)
        fetch(baseURL + "verifyCardInfo",
            {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userID: '1',
                    bikeID: '1',
                    cardID: status.cardID,
                    userName: status.cardOwner,
                    cvv: status.cvv,
                    expireDate: status.expireDate
                })
            }
        ).then((response) => response.json())
            .then((json) => {
                console.log("\n------------------------------\n")
                console.log(json)
                resp = json
            })
            .catch((error) => {
                console.error(error);
            })
            .finally(() => {
            })
        
        if (resp.status === "false"){
            alert("INVALID !!!")
        }
        else {
            fetch(baseURL + "prepayInfo",
            {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userID: '1',
                    bikeID: '1',
                    cardID: status.cardID,
                    userName: status.cardOwner,
                    cvv: status.cvv,
                    expireDate: status.expireDate
                })
            }
        ).then((response) => response.json())
            .then((json) => {
                console.log("\n------------------------------\n")
                console.log(json)
                props.navigation.navigate("PrepayScreen", {data: json})
            })
            .catch((error) => {
                console.error(error);
            })
            .finally(() => {
            })

        }
    }

    return (
        <View>
            <SubHeader title="Thông tin tài khoản" />
            <View style={styles.textwap}>
                <View style={styles.customMargin}>
                    <Text style={{ marginTop: -10, textAlign: 'center', color: '#636262' }}> Thông tin thanh toán</Text>
                </View>
                {
                    Object.keys(paymentElemtJson).map((key, vlue) => {
                        return (
                            <Element key={key} name={key}></Element>
                        )
                    })
                }
            </View>
            <View style={styles.buttonWap}>
                <TouchableHighlight
                    style={styles.submit}
                    onPress={sendInfo}
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
            <View style={[styles.keyValues]}>
                <View style={{ justifyContent: 'center', alignItems: 'center' }}>
                    <Text style={[styles.detailText, { fontSize: 13 }]}>{props.name} :</Text>
                </View>
                <TextInput style={[styles.detailText, { flex: 1, fontWeight: '500', fontSize: 15 }]} defaultValue=""
                    onChangeText={text => { //console.log(text); console.log(props)
                        if (props.name === "Mã thẻ") {
                            status.cardID = text
                            // console.log(status.cardID)
                        }
                        else if (props.name === "Chủ thẻ") {
                            // setCardOwner(text)
                            status.cardOwner = text
                        }
                        else if (props.name === "CVV") {
                            // setCvv(text)
                            status.cvv = text
                        }
                        else if (props.name === "Ngày hết hạn") {
                            // setExpireDate(text)
                            status.expireDate = text
                        }
                    }} />
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
        marginTop: "10%",
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
        height: '50%',
        width: '85%',
        marginLeft: '8%',
        marginTop: '15%',
        borderRadius: 15,
        borderWidth: 1,
        borderColor: '#fff'
    },
    elementBd: {
        height: '20%',
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
};