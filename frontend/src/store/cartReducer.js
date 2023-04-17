const initialState= {
    quantity : 0,
    amount: 0
}



const cartReducer = (state =initialState, action) => {
    switch (action.type) {
        case 'quantity/increment':
            return {
                quantity: state.quantity + 1,
                amount: state.amount
            }
        case 'quantity/decrement':
            return {
                quantity: state.quantity - 1,
                amount: state.amount
            }
        case 'amount/increment':
            return {
                quantity: state.quantity,
                amount: state.amount + Number(action.amountt)
            }
        case 'amount/decrement':
            return {
                quantity: state.quantity,
                amount: state.amount - Number(action.amountt)
            }
        case 'set/quantity':
            return {
                quantity: action.newquantity,
                amount: state.amount
            }
        default:
            return state
    }
}

export default cartReducer