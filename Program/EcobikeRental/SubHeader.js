import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight } from 'react-native';
import { AntDesign } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';

export default function SubHeader(props) {
    const navigation = useNavigation();
    return (
        <View style={styles.container}>
            <AntDesign name='arrowleft' size={32} color='white'
                style={{ marginLeft: 20, marginTop: 15 }}
                onPress={() => {
                    navigation.goBack();
                }} />
            <Text style={{
                color: '#fff', fontSize: 22,
                fontWeight: 'bold', marginLeft: 15, marginTop: 15
            }}>
                {props.title}
            </Text>
        </View>
    );
}

const styles = {
    container: {
        // flex: 1,
        height: '10%',
        flexDirection: 'row',
        alignItems: 'center',
        backgroundColor: '#08BD5F'
    }
}