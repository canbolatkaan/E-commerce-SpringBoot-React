import Box from '@mui/joy/Box';

import Typography from '@mui/joy/Typography';


import { common } from '@mui/material/colors';

import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Badge from '@mui/material/Badge';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import {  useNavigate} from 'react-router-dom'
import React, { useEffect} from 'react';
import axios from 'axios';
import {useSelector} from 'react-redux'



export default function Navbar() {
    let navigate = useNavigate();
    const quantity = useSelector( state =>state.quantity)

    //Dropdown menu
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);

    };
    const handleMenuItem = (number) => {
        handleClose()
        navigate("/products/"+ number)

    };


    //Get Categorıes    

    const [categories,setCategories] = React.useState([]);


useEffect(()=>{
  const fetchData = async () =>{
    try {
      const response = await axios.get('http://localhost:8080/categories');
      setCategories(response.data);
    } catch (error) {
      console.error(error.message);
    }
  }

  fetchData();

},[]);

    //Cart

    const handleCart=() =>{
      navigate("/cart")
   }

  return (
    <>
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            id="basic-button"
            aria-controls={open ? 'basic-menu' : undefined}
            aria-haspopup="true"
            aria-expanded={open ? 'true' : undefined}
            onClick={handleClick}
          >
            Categories<MenuIcon />
          </IconButton>
          <Menu
            id="basic-menu"
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
            MenuListProps={{
            'aria-labelledby': 'basic-button',
            }}
          > 
                
              
                {
                    categories.map( category => 
                      <MenuItem onClick={ () => handleMenuItem(category.categoryId)}>{category.categoryName}</MenuItem>
                        

                    )
                }
                    
                    
          </Menu>
          <Typography variant="h6" component="div" sx={{color:common, flexGrow: 1 }}>
                
          </Typography>
          <IconButton onClick={() => {
                                      handleCart()
                               }} 
                      aria-label="cart">
            <Badge badgeContent={quantity} color="secondary">
                <ShoppingCartIcon />
            </Badge>
          </IconButton>
        </Toolbar>
      </AppBar>
    </Box>
    </>
  );
}
