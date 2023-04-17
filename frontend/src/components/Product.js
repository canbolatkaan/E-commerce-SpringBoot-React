import React,{useEffect,useState} from 'react'
import { useParams} from "react-router-dom";
import axios from 'axios';
import Box from '@mui/joy/Box';
import Typography from '@mui/joy/Typography';
import Button from '@mui/joy/Button';
import Card from '@mui/joy/Card';
import CardMedia from '@mui/material/CardMedia';
import {useDispatch} from 'react-redux'


export default function Product(){
    const dispatch = useDispatch();
    const { productId } = useParams();
    const initialProduct = {productId:1,productName: "", salesPrice:0, category:{categoryName:""}}
    const [product, setProduct] = useState(initialProduct)
    
    
    useEffect(()=>{
        const fetchData = async () =>{
        try {
            const response = await axios.get(`http://localhost:8080/product/${productId}`);
            setProduct(response.data);
        } catch (error) {
            console.error(error.message);
        }
        }
    
        fetchData();
    
    },[productId]);

    const cartIncrement=(product) =>{
        dispatch({
            type : 'quantity/increment',
          })
        dispatch({
            type : 'amount/increment',
            amountt: product.salesPrice
        })
        axios.post(`http://localhost:8080/cart/add/1/${product.productId}`);
     }
    return(
        
            <Card variant="outlined" >
                <Typography level="h2" fontSize="md" sx={{ mb: 0.5 }}>
                {product.productName}
                </Typography>
                
                <CardMedia
                    component="img"
                    sx={{ height: 320, padding: "1em 1em 0 1em", objectFit: "contain" }}
                    image={require("../images/products/"+product.category.categoryName+product.productId+".jpg")}
                    title="green iguana"
                />
                <Box sx={{ display: 'flex' }}>
                    <div>
                    <Typography level="body3">
                        Total price:
                    </Typography>
                    <Typography fontSize="lg" fontWeight="lg">
                        {product.salesPrice}$
                    </Typography>
                    </div>
                    <Button
                        variant="solid"
                        size="sm"
                        color="primary"
                        aria-label="Explore Bahamas Islands"
                        sx={{ mt:2, ml:'auto', mr:1, fontWeight: 600 }}
                        onClick={() => cartIncrement(product)}
                    >
                        Add to Cart
                    </Button>
                    
                </Box>
            </Card>
    );
}