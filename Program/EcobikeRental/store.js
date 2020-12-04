import { createStore } from 'redux';

// initialState
const initialState = {}

// root reducer
const rootReducer = (state, action) => {
    switch (action.type) {
        case 'STORE_UPFRONT_PRICE': return Object.assign({}, state, { price: action.price })
        default: return state;
    }
}

// Create store
const store = createStore(rootReducer, initialState)

export default store;