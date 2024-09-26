package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.ui.theme.ShoppingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingApp(modifier: Modifier = Modifier) {
    val products = listOf(
        Product("Product A", "$100", "This is a great product A."),
        Product("Product B", "$150", "This is product B with more features."),
        Product("Product C", "$200", "Premium product C."),
        Product("Product D", "$1000", "Extremely premium product D.")
    )
    val portraitOrientation = false // placeholder

    if (portraitOrientation){
        PortraitView(products)
    }
    else{
        LandScapeView(products)
    }
}

class Product(
    val name: String,
    val price: String,
    val description: String
)


@Composable
fun PortraitView(products: List<Product>){
//    ProductList(products)
}

@Composable
fun LandScapeView(products: List<Product>){
    val defaultProduct = Product("", "Please Select a Product", "")
    var i by remember { mutableIntStateOf(-1) }
    val makeSelectProduct: (Int) -> () -> Unit = { it ->
        { i = it }
    }

        Row {
        ProductList(products,  modifier = Modifier
            .weight(1f)
            .fillMaxHeight(), makeSelectProduct = makeSelectProduct)
        ProductDetails(
            product = if (i == -1){
                defaultProduct
            }
            else{
                products[i]
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}


@Composable
fun ProductList(products: List<Product>, modifier: Modifier = Modifier, makeSelectProduct:  (Int) -> () -> Unit){
    Column (modifier = modifier){
        Text(
            "Product List",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 40.sp
            ),
            modifier = Modifier.padding(top = 30.dp, start = 9.dp)

        )
        LazyColumn (
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            itemsIndexed(products) { index, product ->
                Card(shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CardDefaults.cardColors().containerColor
                    ),
                    onClick = makeSelectProduct(index)){
                    Column(
                        modifier = Modifier
                            .padding(14.dp)
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 25.sp,
                            )
                        )
                        Text(
                            text = "${product.price} | ${product.description}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )
                        )

                    }
                }
            }

        }
    }
}

@Composable
fun ProductDetails(product: Product, modifier: Modifier = Modifier){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .aspectRatio(1f)
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardDefaults.cardColors().containerColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 25.sp,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 22.sp,
                        color = Color.Gray,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingAppTheme {
        ShoppingApp()
    }
}