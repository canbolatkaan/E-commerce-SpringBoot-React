import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import TextField from '@mui/material/TextField';
import React,{useEffect,useState} from 'react'
import axios from 'axios';
import {useDispatch} from 'react-redux'
import '../App.css';
import {useSelector} from 'react-redux'


export default function Product(){
    const quantity = useSelector( state =>state.quantity)
    const amount = useSelector( state =>state.amount)

    const dispatch = useDispatch();
    const [username, setUsername] = useState('');
    const [cardnumber, setCardnumber] = useState('');
    const [cartProducts,setCartProducts] = useState([])
    const [error, setError] = useState(false);
    
    const handleCheckoutClick = () => {
        if (!cardnumber || !username) {
            setError(true);
            return null;
        }
        else{
            setError(false);
            alert("Sipariş Oluşturuldu")

            axios({
                method: 'put',
                url: '/api/article/123',
                data: {
                    cartId: 1,
                    customerName: username,
                    cardNumber: Number(cardnumber),
                    cartStatus: "NEW"
                }
            });

            return null;
        }
    };
    const handleUsernameChangeValue = (e) => {
        setUsername(e.target.value);
    };
    const handleCardnumberChangeValue = (e) => {
        setCardnumber(e.target.value);
    };
    const cartIncrement=(cartproduct) =>{
        dispatch({
          type : 'quantity/increment',
        })
        dispatch({
            type : 'amount/increment',
            amountt: cartproduct.product.salesPrice
          })
        axios.post(`http://localhost:8080/cart/add/1/${cartproduct.product.productId}`);
     }
     const cartDecrement=(cartproduct) =>{
        dispatch({
          type : 'quantity/decrement',
        })
        dispatch({
            type : 'amount/decrement',
            amountt: cartproduct.product.salesPrice
        })
        axios.get(`http://localhost:8080/cart/remove/1/${cartproduct.product.productId}`);
     }

     useEffect(()=>{
        const fetchData = async () =>{
        try {
            const response = await axios.get(`http://localhost:8080/cart/get/cardProducts/1`);
            setCartProducts(response.data);
        } catch (error) {
            console.error(error.message);
        }
        }
    
        fetchData();
    
    },[quantity]);
    
return(
    <Container maxWidth="lg">
        <Grid container spacing={5}>
            <Grid item md={9}>
                <div className="cart_list">
                    <h2>Shopping Cart </h2>
                    <Grid className="title" container spacing={3}>
                        <Grid className="delete" item md={1}></Grid>
                        <Grid className="detail" item md={5}>
                        PRODUCTS DETAIL
                        </Grid>
                        <Grid item md={2}>
                        QUANTITY
                        </Grid>
                        <Grid item md={2}>
                        PRICE
                        </Grid>
                        <Grid item md={2}>
                        TOTAL
                        </Grid>
                    </Grid>
                    {cartProducts.map( item => (
                    <div className="cart_item">
                        <Grid className="title_item" alignItems="center" container spacing={3}>
                            <Grid className="delete" item md={1}>
                            
                            </Grid>
                            <Grid className="detail" item md={5}>
                            <CardMedia
                                component="img"
                                sx={{ height: 320, padding: "1em 1em 0 1em", objectFit: "contain" }}
                                image={require("../images/products/"+item.product.category.categoryName+item.product.productId+".jpg")}
                                title="green iguana"
                            />
                            <div className="info">
                                <h3>{item.product.productName}</h3>
                                
                            </div>
                            </Grid>
                            <Grid item md={2}>
                            <ButtonGroup variant="text" aria-label="text button group">
                                    <Button onClick={() => cartIncrement(item)}>+</Button>
                                    <Button>{item.salesQuantity}</Button>
                                    <Button onClick={() => cartDecrement(item)}>-</Button>
                                    </ButtonGroup>
                            </Grid>
                            <Grid item md={2}>
                            {item.product.salesPrice}$
                            </Grid>
                            <Grid item md={2}>
                            {item.product.salesPrice * item.salesQuantity}$
                            </Grid>
                        </Grid>
                    </div>
                    ))}
                </div>
            </Grid>
            <Grid item md={3}>
            <div className="order">
            
      <h3 className="title">ORDER SUMMARY</h3>
      <ul className="order_info">
        
        <li>
          <h3>Delivery costs :</h3>
          <p>15$</p>
        </li>
        <li>
          <h3>Total :</h3>
          <p className="total">{amount+15}$</p>
        </li>
        <li>
            <TextField
            required
            label="Required Username"
            value={username}
            error={!!error}
            name="Username"
            onChange={(e) => handleUsernameChangeValue(e)}
            helperText={error ? 'this is required' : ''}
            />
        </li>
        <li>
            <TextField
            required
            label="Required Cardnumber"
            value={cardnumber}
            error={!!error}
            name="Cardnumber"
            onChange={(e) => handleCardnumberChangeValue(e)}
            helperText={error ? 'this is required' : ''}
            />
        </li>
        <li>
          <button onClick={handleCheckoutClick} type="submit">
            Checkout
          </button>
        </li>
      </ul>
    </div>
    </Grid>
          
        </Grid>
      </Container>
);
}