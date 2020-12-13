import { createStore } from 'redux';

// initialState
const initialState = {}

// root reducer
const rootReducer = (state, action) => {
    switch (action.type) {
        case 'STORE_UPFRONT_PRICE': return Object.assign({}, state, { price: action.price })
        case 'STORE_CVV': return Object.assign({}, state, {cvv: action.value})
        case 'STORE_EXPIREDATE': return Object.assign({}, state, {expireDate: action.value})
        case 'STORE_BIKEID': return Object.assign({}, state, {bikeID: action.value})
        case 'STORE_CARDID': return Object.assign({}, state, {cardID: action.value})
        case 'STORE_PARKINGLOTID': return Object.assign({}, state, {parkingLotID: action.value})
        default: return state;
    }
}

// Create store
const store = createStore(rootReducer, initialState)

export default store;