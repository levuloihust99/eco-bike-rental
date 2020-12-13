import React from 'react';
import { Text, View, Image, Button, TouchableHighlight } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import SubHeader from './SubHeader';
import { baseURL } from './config'
import { useStore } from 'react-redux'

const RentingDetailScreen = (props) => {
	var defaultRentingElemtJson = {
		'Loại xe': 'Yamaha Icat4',
		'Số tiền tạm tính': '150.000VND',
		'Thời gian còn lại ước tính': '30%',
		'Mã vạch': "123542X35sjs"
	}

	const [timer, setTimer] = React.useState({
		hour: 0,
		minute: 0,
		second: 0,
	});

	const store = useStore()
	const [resp, setResp] = React.useState('initial')
	const [flag, setFlag] = React.useState(true)
	const [rentingElemtJson, setRentingElemtJson] = React.useState(defaultRentingElemtJson)

	React.useEffect(() => {
		if (flag) {
			fetch(baseURL + "getRentTransaction",
				{
					method: 'POST',
					headers: {
						Accept: 'application/json',
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						cardID: store.getState().cardID
					})
				}
			).then((response) => response.json())
				.then((json) => {
					setResp(json)
				})
				.catch((error) => {
					console.error(error);
				})
				.finally(() => {
				})
		}
	}, [flag])

	React.useEffect(() => {
		console.log(resp)
		if (resp !== 'initial'){
			let now = new Date()
			let startTime = resp.TimeStamp
			let rentTime = Math.round((now - startTime) / 1000)

			setTimer({
				hour: Math.floor(rentTime / 3600),
				minute: Math.floor((rentTime % 3600) / 60),
				second: Math.floor((rentTime % 3600) % 60)
			})

			console.log(resp)
			setRentingElemtJson(
				Object.keys(resp)
					.filter(key => key !== 'TimeStamp')
					.reduce((result, current) => {
						result[current] = resp[current];
						return result;
					}, {})
			)
		}
	}, [resp])

	React.useEffect(() => {
		const timeoutID = setTimeout(() => {
			let nextSecond = timer.second + 1;
			let nextMinute = timer.minute;
			let nextHour = timer.hour;

			if (nextSecond > 59) {
				nextMinute += 1;
				nextSecond = 0;
				if (nextMinute > 59) {
					nextHour += 1;
					nextMinute = 0;
				}
			}
			setTimer({ hour: nextHour, minute: nextMinute, second: nextSecond });
		}, 1000);

		return () => clearTimeout(timeoutID);
	}, [timer]);

	return (
		<View style={styles.container}>
			<SubHeader title="Thông tin xe đang thuê" />
			<View style={styles.textwap}>
				<View style={styles.customMargin}>
				</View>
				{
					Object.keys(rentingElemtJson).map((key, vlue) => {
						return (
							<Element key={key} name={key} detail={rentingElemtJson[key]}></Element>
						)
					})
				}
			</View>
			<View style={[styles.textwap, { height: '15%', marginTop: '7%', backgroundColor: '#08BD5F' }]}>
				<Text style={{ textAlign: 'center', color: '#ffff', marginTop: 4 }}>
					THỜI GIAN THUÊ
            </Text>
				<Text style={{ textAlign: 'center', color: '#ffff', fontWeight: 'bold', fontSize: 35, marginTop: 4 }}>
					{timer.hour < 10 ? '0' + timer.hour : timer.hour} : {timer.minute < 10 ? '0' + timer.minute : timer.minute} : {timer.second < 10 ? '0' + timer.second : timer.second}
				</Text>
			</View>
			<View style={styles.rowSeparate}>
				<View style={{ width: '30%', alignItems: 'center' }}>
					<MaterialIcons name="pause-circle-outline" size={75} color='#08BD5F' />
					<Text>Tạm dừng</Text>
				</View>
				<View style={{ width: '30%', alignItems: 'center' }}>
					<MaterialIcons name="keyboard-return" size={75} color="#08BD5F"
						onPress={() => props.navigation.navigate('ReturnPackingLotScreen')} />
					<Text>Trả xe</Text>
				</View>
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
	rowSeparate: {
		flexDirection: 'row',
		marginTop: '4%',
		justifyContent: 'center'
	},
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
		height: '45%',
		width: '85%',
		marginLeft: '8%',
		marginTop: '10%',
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
	submitText: {
		color: '#fff',
		textAlign: 'center',
	},


};
export default RentingDetailScreen;