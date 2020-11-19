import React from 'react';
import { StyleSheet, Text, View, Image, ScrollView, TouchableHighlight, Button } from 'react-native';
import HeaderCompo from './Header';
import PackingLotElement from './PackingLotElement';

export default class HomeScreen extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			data: defaultData,
			isLoading: true,
			status: 'renting',
		};
		this.buttonCondition = this.buttonCondition.bind(this);
	}

	UNSAFE_componentWillMount() {
		fetch('https://99268e9cd5ff.ngrok.io/listParkingLot',
			{
				method: 'POST',
				headers: {
					Accept: 'application/json',
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					type: 'packing_lot',
					number: 6
				})
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
			.finally((json) => {
				this.setState({ isLoading: false });
			});
	}

	componentDidMount() {
		this._unsubscribe = this.props.navigation.addListener('focus', () => {
			if (this.props.route.params?.status) {
				this.setState({
					status: this.props.route.params.status
				})
			}
		});
	}

	componentWillUnmount() {
		this._unsubscribe();
	}

	buttonCondition = () => {
		if (this.state.status === 'renting') {
			return (
				<TouchableHighlight
					style={styles.submit}
					onPress={() => this.props.navigation.navigate('RentBike')}
					underlayColor='#ffff'>
					<Text style={[styles.submitText]}>Thuê xe</Text>
				</TouchableHighlight>
			);
		} else if (this.state.status === 'rented') {
			return (
				<TouchableHighlight
					style={[styles.submit, { marginLeft: '25%', width: '50%' }]}
					onPress={() => this.props.navigation.navigate('RentingDetailScreen')}
					underlayColor='#FA2626'>
					<Text style={[styles.submitText]}>XEM XE ĐANG THUÊ</Text>
				</TouchableHighlight>
			);
		} else {
			return (
				<TouchableHighlight
					style={styles.submit}
					onPress={() => this}
					underlayColor='#ffff'>
					<Text style={[styles.submitText]}> {this.state.myState}</Text>
				</TouchableHighlight>
			);
		};
	}
	render() {
		return (
			<View style={styles.container}>
				<HeaderCompo style={styles.header} />
				<Text>{this.props.route.title}</Text>
				<Image style={styles.body} source={require('./image/4871.jpg')} />
				{  this.buttonCondition()}
				<ScrollView>
					{this.state.data.map((y) => {
						return (
							<PackingLotElement key={y['id']} name={y['name']} address={y['address']}> </PackingLotElement>
						)
					})}
				</ScrollView>
			</View>
		);
	}
}


const defaultData =
	[
		{
			id: 1,
			name: 'Vườn Hướng Dươnggggggggg',
			address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
		},
		{
			id: 2,
			name: 'Vườn Hướng Dươnggggggggg',
			address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
		},
		{
			id: 3,
			name: 'Vườn Hướng Dươnggggggggg',
			address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
		},
		{
			id: 4,
			name: 'Vườn Hướng Dươnggggggggg',
			address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
		},
		{
			id: 5,
			name: 'Vườn Hướng Dươnggggggggg',
			address: 'Số 4/6, Đường Nguyễn Trãiiiiiii'
		}
	];


const styles = StyleSheet.create({
	container: {
		width: '100%',
		height: '100%',
		flexDirection: 'column',
		// alignItems : 'center'
		// flex :1 
	},
	header: {
		width: '20%',
		height: '30%',

	},
	body: {
		width: '100%',
		height: '30%',

	},
	submit: {
		// flex:1,
		height: '7%',
		width: '40%',

		// marginRight:40,
		marginLeft: '30%',
		marginTop: -20,
		// paddingTop:10,
		// paddingBottom:10,
		backgroundColor: '#08BD5F',
		borderRadius: 6,
		borderWidth: 1,
		borderColor: '#fff'
	},
	submitText: {
		color: '#fff',
		textAlign: 'center',
		marginTop: 10,
	}
});