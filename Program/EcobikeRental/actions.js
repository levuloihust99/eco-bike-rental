// action creator
const storeUpFrontPrice = (price) => ({
    type: 'STORE_UPFRONT_PRICE',
    price
})

const storeCVV = (value) => ({
    type: 'STORE_CVV',
    value
})

const storeExpireDate = (value) => ({
    type: 'STORE_EXPIREDATE',
    value
})

const storeBikeID = (value) => ({
    type: 'STORE_BIKEID',
    value
})

const storeCardID = (value) => ({
    type: 'STORE_CARDID',
    value
})

const storeParkingLotID = (value) => ({
    type: 'STORE_PARKINGLOTID',
    value
})

export { storeUpFrontPrice, storeCVV, storeExpireDate, storeBikeID, storeCardID, storeParkingLotID }