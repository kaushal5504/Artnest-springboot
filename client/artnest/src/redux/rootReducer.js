import {combineReducers, applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
import userReducer from './user/reducers'
import productReducer from './product/reducers'
const rootReducer = combineReducers({
    
    user : userReducer,
    product : productReducer
}
)

console.log("rootreducer is called")

export default rootReducer