$(document).ready(function(){
    var token = localStorage.getItem("token")
    var parentUrlApi = "http://localhost:8080/product"
    let searchParams = new URLSearchParams(window.location.search)
    let categoryParam = searchParams.get('category')
    var apiCategory = `http://localhost:8080/product/getProductByCategoryName?name=${categoryParam}`
    

    $.ajax({
        type: "GET",
        url:apiCategory,
        headers:{
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        success:function(rs){
            
           
            var renderProduct = function(data){
               
                    var html =`
                    <div class="categoryTitle">
                        <h1>${categoryParam}</h1>
                    </div>`
    
                    var productItem =``;
                    $.each(data,function(index,item){
                        productItem += `
                        <div class="product__item">
                            <div class="product__item__img">
                                <a href="detailProduct.html?id=${item.id}">
                                    <img src="${parentUrlApi}/file/${item.image[0].image}" alt="">
                                </a>
                            </div>
                            <div class="product__item__size">
                                <div class="active size">Xanh</div>
                                <div class="size">Đỏ</div>
                                <div class="size">Nâu</div>
                            </div>
                            <div class="product__item__title">
                                <a href="">${item.title}</a>
                            </div>
                            <div class="product__item__price">
                                <p>${item.priceList[0].price}.000đ</p>
                            </div>
                        </div>`
                    })
                    html +=`
                    <div class="product">
                        <div class="product__row">
                            ${productItem}
                        </div>
                        
                    </div>`

                    $('.product-section').empty().append(html);
                
            }
            renderProduct(rs.data);
            var renderSiteBarLeft = function(data){
                   
                    var countProduct = data.length;
                    var html = `
                    <div class="filter__count">
                        <b>${countProduct} kết quả</b>
                    </div>
                    <ul class="filter__item">
                        <h5>Mức giá</h5>
                        <li>
                            <input type="checkbox" name="choicePrice" id="choice1">
                            <label for="choice1"><span>Giá cao đến thấp</span></label>
                        </li>
                        <li>
                            <input type="checkbox" name="choicePrice" id="choice2">
                            <label for="choice2"><span>Giá thấp đến cao</span></label>
                        </li>
                    </ul>
                    <ul class="filter__item">
                        <h5>Sản phẩm mới</h5>
                        <li>
                            <input type="checkbox" id="Choice3">
                            <label for="Choice3"><span>Sản phẩm mới nhất</span></label>
                        </li>
                        
                    </ul>
                    <ul class="filter__item">
                        <h5>Đánh giá</h5>
                        <li>
                            <input type="checkbox" id="Choice4" name="choiceFb">
                            <label for="Choice4"><span>5 sao trở lên</span></label>
                        </li>
                        <li>
                            <input type="checkbox" id="Choice5" name="choiceFb">
                            <label for="Choice5"><span>4 sao trở lên</span></label>
                        </li>
                        <li>
                            <input type="checkbox" id="Choice6" name="choiceFb">
                            <label for="Choice6"><span>3 sao trở lên</span></label>
                        </li>
                    </ul>
                    `;

                
                $('.filter-option').empty().append(html);
            }
            renderSiteBarLeft(rs.data)

            var handleRenderProduct = function(){
                $('.filter__item input[type=checkbox]').on("click",function(){
                   
                    var filteredData = filterData();
                    renderProduct(filteredData) 
                    
                })
                function filterData(){
                    let dataInput = rs.data;

                    // filter price data
                    var priceOption = $('input[name=choicePrice]:checked').attr('id');
                    if(priceOption === "choice1"){
                        dataInput = dataInput.sort(function(item1,item2){
                            return item2.priceList[0].price - item1.priceList[0].price
                        })
                    }else if(priceOption === "choice2"){
                        dataInput = dataInput.sort(function(item1,item2){
                            
                            return item1.priceList[0].price - item2.priceList[0].price
                        })
                    }
                    

                    //filter rating star data
                    var starOption = $('input[name=choiceFb]:checked').attr('id');
                    if(starOption === "Choice4"){
                        dataInput = dataInput.filter(function(item){
                            var point = 0;
                            
                            $.each(item.ratingStar,function(index2,item2){
                                point += item2.ratePoint
                               
                            })
                            point = point / item.ratingStar.length;
                            console.log(point)
                            if(point >= 5){
                                
                                return item;
                            }
                        })
                    }else if(starOption === "Choice5"){
                        dataInput = dataInput.filter(function(item){
                            let point = 0;
                            $.each(item.ratingStar,function(index2,item2){
                                point += item2.ratePoint
                            })
                            point = point / item.ratingStar.length;
                            if(point >= 4){
                                return item;
                            }
                        })
                    }else if(starOption === "Choice6"){
                        dataInput = dataInput.filter(function(item){
                            let point = 0;
                            $.each(item.ratingStar,function(index2,item2){
                                point += item2.ratePoint
                            })
                            point = point / item.ratingStar.length;
                            if(point >= 3){
                                return item;
                            }
                        })
                    }

                    // filter new product 
                  
                    return dataInput;
                }
            }
            
            
            
            
           
            handleRenderProduct();
        
        }
    })
})