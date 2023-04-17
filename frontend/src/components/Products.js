import React,{useEffect,useState} from 'react'
import { useParams,useNavigate } from "react-router-dom";
import axios from 'axios';
import Box from '@mui/joy/Box';
import Grid from '@mui/joy/Grid';
import Typography from '@mui/joy/Typography';
import Button from '@mui/joy/Button';
import Card from '@mui/joy/Card';
import CardMedia from '@mui/material/CardMedia';
import {useDispatch} from 'react-redux'



export default function Products(){
  const dispatch = useDispatch();

    let navigate = useNavigate();
    const { categoryId } = useParams();

    const [products,setProducts] = useState([])


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

    
    useEffect(()=>{
        const fetchData = async () =>{
        try {
            const response = await axios.get(`http://localhost:8080/products/${categoryId}`);
            setProducts(response.data);
        } catch (error) {
            console.error(error.message);
        }
        }
    
        fetchData();
    
    },[categoryId]);
    
    const handleDetails=(productId) =>{
        navigate("/product/"+ productId)
    }
    

    return(<Box sx={{ flexGrow: 1 }}>
        <Grid
          container
          spacing={2}
          sx={{
            '--Grid-borderWidth': '1px',
            borderTop: 'var(--Grid-borderWidth) solid',
            borderLeft: 'var(--Grid-borderWidth) solid',
            borderColor: 'divider',
            '& > div': {
              borderRight: 'var(--Grid-borderWidth) solid',
              borderBottom: 'var(--Grid-borderWidth) solid',
              borderColor: 'divider',
              
            },
          }}
        >
          {products.map( product =>(
            <Grid
              key={product.productName}
              xs={12}
              sm={6}
              md={3}
              display="flex"
              justifyContent="center"
              alignItems="center"
              minHeight={180}
              sx = {{pt:2}}
            >
              <Card variant="outlined" sx={{ width: 320 }}>
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
            <Typography level="body3">Total price:</Typography>
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
          <Button
            variant="solid"
            size="sm"
            color="primary"
            aria-label="Explore Bahamas Islands"
            sx={{ mt:2, ml: 'auto', fontWeight: 600 }}
            onClick={ () => handleDetails(product.productId)}
          >
            Detail
          </Button>
        </Box>
      </Card>
            </Grid>
          ))}
        </Grid>
      </Box>
        
    )
}