$(document).ready(function(){
    var urlApi = "http://localhost:8080/product/getProductById";
    var parentUrlApi = "http://localhost:8080/product"
    var token = localStorage.getItem("token")
    let searchParams = new URLSearchParams(window.location.search)
    let param = searchParams.get('id')

   
    
    $.ajax({
        
        type: "POST",
        url: `${urlApi}?id=${param}`,
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        success: function (rs) {
            
            if(rs.succes){
               
               var value = rs.data
               //image item
               var imageItemHtml = "";
               $.each(value.image,function(index,item){
                   if(index == 0){
                       imageItemHtml += `
                   <div class="image__item active">
                       <img src="${parentUrlApi}/file/${item.image}" alt="">
                   </div>`
                   }else{
                    imageItemHtml += `
                   <div class="image__item">
                       <img src="${parentUrlApi}/file/${item.image}" alt="">
                   </div>`
                   }
               })
               
               // Picture product
               var mainPictureHtml = ` 
               <div class="product__image">
                   <img src="${parentUrlApi}/file/${value.image[0].image}" alt="">
                   <div class="image__box">
                       ${imageItemHtml}
                   </div>
                   
               </div>
               
           `
               $('#product').append(mainPictureHtml)

               // product size option
                var sizeProductHtml = "";
                $.each(rs.data.priceList,function(index,item){
                    sizeProductHtml += `<div class="option__item">${item.size.sizeName}</div>`
                })


                // color option render
                var colorImageOptionHtml = "";
                const colorImageOption = rs.data.image.reduce((result, currentValue) => {
                    const color = currentValue.color.color
                    
                    if (!result.includes(color)) {
                        result.push(color);
                            colorImageOptionHtml += `<div class="option__item">${color}</div>`
                    }

                    return result;
                }, []);

                // product star rating
                
                var userFeedBackHtml ="";
                var userRatingPoint = 0;
                
                const counts = {};
                var ratingStar = 0;
                var ratingStarIndex = 0;
                $.each(value.ratingStar,function(index,item){
                    ratingStarIndex ++;
                    ratingStar += item.ratePoint
                    counts[item.ratePoint] = (counts[item.ratePoint] || 0) + 1;
                    var userRatingPointHtml = "";
                    userRatingPoint = item.ratePoint
                    for(let i=0;i<userRatingPoint;i++){
                        userRatingPointHtml += `<img src="/img/icon/star.png" alt="">`
                    }
                    
                        userFeedBackHtml += `
                    <div class="feedback">
                        <div class="feedback__star">
                           ${userRatingPointHtml}
                        </div>
                        <div class="feedback_user">
                            <div class="user__avatar">
                                <img src="/img/icon/gamer.png" alt="">
                                <div class="avatar__box">
                                    <p>${item.user.userName}</p>
                                    <i>${rs.data.title}</i>
                                </div>
                            </div>
                            <div class="user__name"></div>
                        </div>
                        <div class="feedback__comment">
                            <span>${item.feedback}</span>
                        </div>
                        <div class="feedback__date">20/12/2024</div>
                    </div>`
                    
                })
                var starAverage = ratingStar / ratingStarIndex
                if(isNaN(starAverage)){
                    starAverage = 5;
                }

                
                
                
                // avarge point or star of product 
                var productStarHtml = "";
                for(let i=0;i<=ratingStarIndex;i++){
                    if(ratingStarIndex == 0){
                        productStarHtml += `<div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>
                                            <div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>
                                            <div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>
                                            <div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>
                                            <div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>`
                                            break;
                    }else {
                        productStarHtml += `<div class="product__star-item">
                                                <img src="/img/icon/star.png" alt="">
                                            </div>`
                    }
                    
                }
                if (starAverage.toFixed(1) % 1 > 0.5) { 
                    productStarHtml += `<div class="product__star-item product__star-half">
                                          <img src="/img/icon/halfStar.png" alt="" style="width: 50%;">
                                      </div>`;
                }
                
                var countUserFb = 0;
                $.each(counts,function(index,item){
                    
                    countUserFb += item
                })
                
                
                var summaryHtml = `
                <div class="product__summary">
                    <div class="product__title">
                        <h1>${value.title}</h1>
                    </div>
                    <div class="product__star">
                        ${productStarHtml}
                        <div class="product__star-item">|</div>
                        <span>Đánh giá:${countUserFb}</span>
                    </div>
                    <div class="product__price">
                        <div class="product__price-regular">
                            ${(value.priceList[2].price)-(value.priceList[2].price*10/100)}.000đ
                        </div>
                        <div class="product__price-compare">
                            ${value.priceList[2].price}.000đ 
                        </div>
                        
                    </div>
                    <div class="product__price-hint">
                        Mua 2 được giảm thêm 10%
                    </div>
                    <div class="product__coupon">
                        <div class="product__coupon__heading">
                            Các mã giảm giá có thể áp dụng:
                        </div>
                        <div class="product__coupon_wrapper">
                            <div class="mini_coupon">
                                Giảm 100k
                            </div>
                            <div class="mini_coupon">
                                Giảm 60k
                            </div>
                        </div>
                    </div>
                    <div class="product__option">
                        <form action="#" class="addToCard">
                            <div class="option__color">
                                <p>Màu sắc:</p>
                                <div class="option__box">
                                    ${colorImageOptionHtml}
                                </div>
                            </div>
                            <div class="option__size ">
                                <p>Kích thước:</p>
                                <div class="option__box">
                                    ${sizeProductHtml}
                                </div>
                            </div>
                            <div class="option__action">
                                <div class="option__quantity">
                                    <span>-</span>
                                    <input value="1"></input>
                                    <span>+</span>
                                </div>
                                <button class="option__addBtn">
                                    <img src="/img/icon/shopping-cart.png" alt="">
                                    <span>Thêm vào giỏ hàng</span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>`
                $('#product').append(summaryHtml)

                var productDecriptionHtml = `
                <div class="description__heading">
                    <h2>Mô tả sản phẩm</h2>
                </div>
                <div class="description__body">
                    <p>- Sản phẩm 100% giống mô tả. Hình ảnh sản phẩm chân thật ,đầy đủ tem, mác, bao bì cao cấp. <br><br>

                        - Hình ảnh sản phẩm là ảnh thật do shop tự chụp và giữ bản quyền hình ảnh<br><br>
                        
                        - Đảm bảo vải chất lượng 100% cotton chuẩn xuất khẩu.<br><br>
                        
                        - Áo được kiểm tra kĩ càng, cẩn thận và tư vấn nhiệt tình trước khi gói hàng giao cho Quý Khách<br><br>
                        
                        - Hàng có sẵn, giao hàng ngay khi nhận được đơn<br><br>
                        
                        - Hoàn tiền nếu sản phẩm không giống với mô tả<br><br>
                        
                        - Chấp nhận đổi hàng khi size không vừa (vui lòng nhắn tin riêng cho shop)<br><br>
                        
                        - Giao hàng trên toàn quốc, nhận hàng trả tiền<br><br>
                        
                        - Hỗ trợ đổi trả theo nội quy của Shopee<br><br>
                        
                        Quý khách nhận được sản phẩm, vui lòng đánh giá giúp Shop để hưởng thêm nhiều ưu đãi hơn nhé.</p>
                </div>
                `      
                $('#description').append(productDecriptionHtml)

                

                var productReviewsHtml =`<div class="reviews__heading">
                                            <div class="review__title">
                                                <h2>Đánh giá sản phẩm</h2>
                                            </div>
                                            <div class="review__star">
                                                <div class="star__point">
                                                    <div class="rating-overview">
                                                        <span>${starAverage.toFixed(1)} </span>
                                                        <span>trên 5</span>
                                                    </div>
                                                    <div class="rating-image">
                                                        ${productStarHtml}
                                                    </div>
                                                </div>
                                                <div class="star__filter">
                                                    <div class="filter__option active">
                                                        Tất cả
                                                    </div>
                                                    <div class="filter__option">
                                                        5 Sao <span> (${counts[5]})</span> 
                                                    </div>
                                                    <div class="filter__option">
                                                        4 Sao <span> (${counts[4]})</span>
                                                    </div>
                                                    <div class="filter__option">
                                                        3 Sao <span> (${counts[3]})</span>
                                                    </div>
                                                    <div class="filter__option">
                                                        2 Sao <span> (${counts[2]})</span>
                                                    </div>
                                                    <div class="filter__option">
                                                        1 Sao <span> (${counts[1]})</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="review__body">
                                            
                                            ${userFeedBackHtml}
                                            
                                        </div>`

                $('#reviews').append(productReviewsHtml)

                

                // filter reviews 
                var filterOptions = $('.star__filter .filter__option span');
                $.each(filterOptions, function(index, item) {
                    let stringItem = `${$(item).text()}`
                 if(stringItem === ' (undefined)'){
                    $(item).text('(0)')
                    
                 }
                });


                // add class active 
                var changeActive = function(element){
                    $.each(element,function(index,item){
                       
                        $(item).on('click',function(){
                            element.removeClass('active')
                            $(item).addClass('active')
                            
                        })
                    })
                }

                var optionItemColor = $('.option__color .option__item')
                changeActive(optionItemColor);

                var optionItemSize = $('.option__size .option__item')
                changeActive(optionItemSize)

                // Change main image to selected image
                var OptionImageSelect = $('.product__image .image__item')
                var ChangeMainImage = function(){
                    $.each(OptionImageSelect,function(index,item){
                        $(item).on('click',function(){
                            OptionImageSelect.removeClass('active')
                            $(item).addClass('active')
                            var imagenow = $(item).find('img')
                            $('.product__image>img').attr('src', imagenow.attr('src'));
                        })
                    })
                }
                ChangeMainImage();
                

                // Increase value and decrease value button for add to cart
                var IncreaseAndDecreaseValue = function(){
                    $('.product__option .option__quantity span').first().on('click',function(){
                        let optionQuantityValue =  parseInt($('.product__option .option__quantity input').val());
                        let newValue = optionQuantityValue - 1
                        if(newValue >= 1){
                            $('.product__option .option__quantity input').val(newValue)
                        }
                        
                    })
    
                    $('.product__option .option__quantity span').eq(1).on('click',function(){
                       let optionQuantityValue =  parseInt($('.product__option .option__quantity input').val());
                        let newValue = optionQuantityValue + 1
                        if(newValue >= 1){
                            $('.product__option .option__quantity input').val(newValue)
                        }
                        
                    })
                }
                IncreaseAndDecreaseValue()
                
            }else{
                console.log("Lỗi")
            }
        }
    })

})