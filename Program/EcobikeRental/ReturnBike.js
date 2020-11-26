import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight, TextInput } from 'react-native';
import SubHeader from './SubHeader';

export default function ReturnBike({navigation}) {
    return (
        <View style={{ flex: 1 }}>
            <SubHeader title="Trả xe" />
            <View style={{ marginTop: 40, marginLeft: 20, marginRight: 20 }}>
                <Text style={{ fontSize: 24, color: '#635F5F' }}>Nhập mã vạch</Text>
                <TextInput style={{
                    height: 40, borderBottomWidth: 0.5,
                    borderColor: '#BDBDBD', marginTop: 20, fontSize: 18
                }} />
                <TouchableHighlight
                    style={styles.submit}
                    onPress={() => navigation.navigate('ReturnDetail')}
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