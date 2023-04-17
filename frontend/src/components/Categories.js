
import React, { useEffect, useState} from 'react';
import axios from 'axios';
export default function Categories() {


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


return(
    <>
        Fetch Categories
        <div>
                <ul>
                {
                    categories.map( category => 
                        <li>
                        <a href="https://www.w3.org/">{category.categoryName}</a>
                        
                        </li>
                        

                    )
                }
                </ul>
        </div>
        <br/>
        </>
);
}