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

export default PaymentInfo = (props) => {
    const [cardID, setCardID] = React.useState(undefined)
    const [cardOwner, setCardOwner] = React.useState(undefined)
    const [cvv, setCvv] = React.useState(undefined)
    const [expireDate, setExpireDate] = React.useState(undefined)
    const [resp, setResp] = React.useState(undefined)

    React.useEffect(() => {
        if (resp?.status === "false"){
            alert("INVALID !!!")
        }
        else if (resp?.status) {
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
                    cardID: cardID,
                    cardOwner: cardOwner,
                    cvv: cvv,
                    expireDate: expireDate
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
    }, [resp])

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
                    bikeID: '1',
                    cardID: cardID,
                    cardOwner: cardOwner,
                    cvv: cvv,
                    expireDate: expireDate
                })
            }
        ).then((response) => response.json())
            .then((json) => {
                console.log("\n------------------------------\n")
                console.log(json)
                setResp(json)
            })
            .catch((error) => {
                console.error(error);
            })
            .finally(() => {
            })
    }

    const changeStatus = (name, text) => {
        if (name === "Mã thẻ") {
            setCardID(text)
        }
        else if (name === "Chủ thẻ") {
            setCardOwner(text)
            // status.cardOwner = text
        }
        else if (name === "CVV") {
            setCvv(text)
            // status.cvv = text
        }
        else if (name === "Ngày hết hạn") {
            setExpireDate(text)
            // status.expireDate = text
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
                            <Element key={key} name={key} action={changeStatus}></Element>
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
                    onChangeText={text => props.action(props.name, text)}
                    />
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