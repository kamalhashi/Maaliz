var hashiApp = angular.module('hashiApp') // gets it

hashiApp.constant('categoryRange', {
	MOTOR_MIN: 2,
	MOTOR_MAX: 500,
	MOTOR_CAR_MIN:4,
    MOTOR_CAR_MAX:200,
    MOTOR_CAR_PARTS_MIN:249,
    MOTOR_CAR_PARTS_MAX:400,
    MOTOR_BOATS_MIN:460,
    MOTOR_BOATS_MAX:480,
    MOTOR_HEAVY_VEHICLE_MIN:401,
    MOTOR_HEAVY_VEHICLE_MAX:420,
    MOTOR_MOTORCYCLES_MIN:430,
    MOTOR_MOTORCYCLES_MAX:451,
    PROPERTY_SALE_MIN: 501,
    PROPERTY_SALE_MAX: 700,
    PROPERTY_SALE_RES_MIN: 504,
    PROPERTY_SALE_RES_MAX: 520,
    PROPERTY_SALE_COMM_MIN: 521,
    PROPERTY_SALE_COMM_MAX: 540,
    PROPERTY_SALE_UNITS_MIN: 541,
    PROPERTY_SALE_UNITS_MAX: 560,
    PROPERTY_SALE_LAND_MIN: 561,
    PROPERTY_SALE_LAND_MAX: 590,
    PROPERTY_RENT_MIN: 701,
    PROPERTY_RENT_MAX: 1200,
    PROPERTY_RENT_RES_MIN: 704,
    PROPERTY_RENT_RES_MAX: 720,
    PROPERTY_RENT_COMM_MIN: 721,
    PROPERTY_RENT_COMM_MAX: 740,
    PROPERTY_RENT_ROOM_MIN: 741,
    PROPERTY_RENT_ROOM_MAX: 750,
    PROPERTY_RENT_SHORT_MONTHLY_MIN: 760,
    PROPERTY_RENT_SHORT_MONTHLY_MAX: 770,
    PROPERTY_RENT_SHORT_DAILY_MIN: 771,
    PROPERTY_RENT_SHORT_DAILY_MAX: 780,
    CLASSIFIED_MIN:2001,
    CLASSIFIED_MAX: 7999,
    CLASSIFIED_BABY_ITEMS_MIN:2004,
    CLASSIFIED_BABY_ITEMS_MAX: 2150,
    CLASSIFIED_BOOKS_MIN:2160,
    CLASSIFIED_BOOKS_MAX: 2350,
    CLASSIFIED_BUSINESS_INDUSTRIAL_MIN:2351,
    CLASSIFIED_BUSINESS_INDUSTRIAL_MAX:2650,
    CLASSIFIED_CAMERA_MIN:2700,
    CLASSIFIED_CAMERA_MAX:3000,
    CLASSIFIED_CLOTHING_MIN:3001,
    CLASSIFIED_CLOTHING_MAX:3300,
    CLASSIFIED_COMPUTER_NETWORKING_MIN:3600,
    CLASSIFIED_COMPUTER_NETWORKING_MAX:3899,
    CLASSIFIED_COMPUTER_MIN:3650,
    CLASSIFIED_COMPUTER_MAX:3669,
    CLASSIFIED_TV_MIN:4420,
    CLASSIFIED_TV_MAX:4449,
    CLASSIFIED_ELECTRONICS_MIN:4200,
    CLASSIFIED_ELECTRONICS_MAX:4499,
    CLASSIFIED_FURNITURE_MIN:4700,
    CLASSIFIED_FURNITURE_MAX:4999,
    CLASSIFIED_GAMING_MIN:5000,
    CLASSIFIED_GAMING_MAX:5299,
    CLASSIFIED_HOME_APPLIANCES_MIN:5300,
    CLASSIFIED_HOME_APPLIANCES_MAX:5599,
    CLASSIFIED_JEWELRY_WATCHES_MIN:5600,
    CLASSIFIED_JEWELRY_WATCHES_MAX:5999,
    COMMUNITY_LOST_FOUND_MIN:9033,
    COMMUNITY_LOST_FOUND_MAX:9300,
    CLASSIFIED_MISC_MIN:6300,
    CLASSIFIED_MISC_MAX:6599,
    CLASSIFIED_MOBILE_MIN:6600,
    CLASSIFIED_MOBILE_MAX:6899,
    CLASSIFIED_PETS_MIN:6900,
    CLASSIFIED_PETS_MAX:7199,
    CLASSIFIED_TICKETS_MIN:7500,
    CLASSIFIED_TICKETS_MAX:7599,
    CLASSIFIED_SPORTS_EQUIPMENT_MIN:7200,
    CLASSIFIED_SPORTS_EQUIPMENT_MAX:7499,
    CLASSIFIED_TOYS_MIN:7600,
    CLASSIFIED_TOYS_MAX:7699,
    CLASSIFIED_BUSINESS_SALE_MIN:2380,
    CLASSIFIED_BUSINESS_SALE_MAX:2399,
    COMMUNITY_MIN:8000,
    COMMUNITY_MAX:9999,
    JOB_MIN: 1201,
    JOB_MAX: 2000
});


hashiApp.constant('categoryName', {
	MOTOR: 'Motor',
	MOTOR_CAR: 'MotorCar',
    MOTOR_CAR_PARTS: 'MotorCarParts',
    MOTOR_BOATS: 'MotorBoats',
    MOTOR_HEAVY_VEHICLE: 'MotorHeavyVehicle',
    MOTOR_MOTORCYCLES: 'Motorcycles',
    PROPERTY_SALE_RES: 'PropertySaleRes', 
    PROPERTY_SALE_COMM: 'PropertySaleComm', 
    PROPERTY_SALE_UNITS: 'PropertySaleUnits', 
    PROPERTY_SALE_LAND: 'PropertySaleLand',
    PROPERTY_RENT_RES: 'PropertyRentRes',
    PROPERTY_RENT_COMM: 'PropertyRentComm',
    PROPERTY_RENT_ROOM: 'PropertyRentRoom',
    PROPERTY_RENT_SHORT_DAILY: 'PropertyRentShortDaily',
    PROPERTY_RENT_SHORT_MONTHLY: 'PropertyRentShortMonthly',
    CLASSIFIED: 'Classified',
    COMMUNITY: 'Community',
    JOB: 'Job'

});


hashiApp.constant('bucketName', {
    productImages: 'https://s3-eu-west-1.amazonaws.com/maaliz.angola.product.images/',
    profileImage: 'https://s3-eu-west-1.amazonaws.com/maaliz.angola.profile.image/',
    profileCV: 'https://s3-eu-west-1.amazonaws.com/maaliz.angola.profile.cv/',
    otherImage: 'https://s3-eu-west-1.amazonaws.com/maaliz.angola.other.images/',
    productLogo: 'https://s3-eu-west-1.amazonaws.com/maaliz.angola.product.logo/'

});


hashiApp.constant('categoryIdConstant', {
	MOTOR_CATEGORY_ID: 3,
	CAR_CATEGORY_ID: 21,
	CAR_PARTS_CATEGORY_ID: 22,
	BOATS_CATEGORY_ID: 23,
	HEAVY_VEHICLE_CATEGORY_ID: 24,
	MOTORCYCLES_CATEGORY_ID: 25,
	SALE_RES_CATEGORY_ID: 321,
	SALE_RES_APARTMENT_CATEGORY_ID: 327,
	SALE_RES_VILLA_CATEGORY_ID: 326,
	SALE_COMM_CATEGORY_ID: 322,
	SALE_UNITS_CATEGORY_ID: 323,
	SALE_LAND_CATEGORY_ID: 324,
	RENT_RES_CATEGORY_ID: 334,
	RENT_RES_VILLA_CATEGORY_ID: 342,
	RENT_RES_APARTMENT_CATEGORY_ID: 343,
	RENT_COMM_CATEGORY_ID: 335,
	RENT_ROOM_CATEGORY_ID: 336,
	RENT_SHORT_DAILY_CATEGORY_ID: 339,
	RENT_SHORT_MONTHLY_CATEGORY_ID: 338,
	CLASSIFIED_CATEGORY_ID: 4,
	CLASSIFIED_BABY_ITEMS_CATEGORY_ID: 555,
	CLASSIFIED_BOOKS_CATEGORY_ID: 609,
	CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID: 667,
	CLASSIFIED_CAMERA_CATEGORY_ID: 765,
	CLASSIFIED_CLOTHING_CATEGORY_ID: 838,
	CLASSIFIED_COLLECTIBLES_CATEGORY_ID: 920,
	CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID: 968,
	CLASSIFIED_COMPUTER_CATEGORY_ID: 972,
	CLASSIFIED_TV_CATEGORY_ID: 1096,
	CLASSIFIED_ELECTRONICS_CATEGORY_ID: 1086,
	CLASSIFIED_FURNITURE_CATEGORY_ID: 1196,
	CLASSIFIED_GAMING_CATEGORY_ID: 1265,
	CLASSIFIED_HOME_APPLIANCES_CATEGORY_ID: 1323,
	CLASSIFIED_WATCHES_CATEGORY_ID: 1371,
	CLASSIFIED_WOMEN_JEWELRY_CATEGORY_ID: 1372,
	CLASSIFIED_MISC_CATEGORY_ID: 1425,
	CLASSIFIED_MOBILE_CATEGORY_ID: 1426,
	CLASSIFIED_TICKETS_VOUCHERS_CATEGORY_ID: 1570,
	CLASSIFIED_PETS_CATEGORY_ID: 1470,
	CLASSIFIED_SPORTS_EQUIPMENT_CATEGORY_ID: 1488,
	CLASSIFIED_TOYS_CATEGORY_ID: 1577,
	COMMUNITY_CATEGORY_ID: 8,
	COMMUNITY_ACTIVITIES_CATEGORY_ID: 1590,
	COMMUNITY_ARTIST_CATEGORY_ID: 1591,
	COMMUNITY_CAR_LIFT_CATEGORY_ID: 1592,
	COMMUNITY_CHARITIES_CATEGORY_ID: 1593,
	COMMUNITY_CHILDCARE_CATEGORY_ID: 1594,
	COMMUNITY_CLASSES_CATEGORY_ID: 1595,
	COMMUNITY_DOMESTIC_CATEGORY_ID: 1597,
	COMMUNITY_FREELANCERS_CATEGORY_ID: 1599,
	COMMUNITY_LOST_FOUND_CATEGORY_ID: 1600,
	COMMUNITY_SERVICES_CATEGORY_ID: 1604,
	COMMUNITY_SPORTS_CATEGORY_ID: 1605,
	COMMUNITY_FREE_STUFF_CATEGORY_ID: 1606,
	JOB_CONSULTING_CATEGORY_ID:385,
	JOB_CONSTRUCTION_CATEGORY_ID:384,
	JOB_ACCOUNTING_CATEGORY_ID: 375,
	JOB_BANKING_FINANCE_CATEGORY_ID: 380,
	JOB_AIRLINES_AVIATION_CATEGORY_ID:376,
	JOB_GOV_ADMIN_CATEGORY_ID:395,
	JOB_MEDIA_CATEGORY_ID: 408,
	JOB_MARKETING_ADVERTISING_CATEGORY_ID:407,
	JOB_SALES_CATEGORY_ID:422,
	JOB_BEAUTY_CATEGORY_ID:381,
	JOB_EDUCATION_CATEGORY_ID:387,
	JOB_OIL_GAS_CATEGORY_ID:410,
	JOB_ENGINEERING_CATEGORY_ID:389,
	JOB_SECRETARIAL_ID:423,
	JOB_HR_RECRUITMENT_CATEGORY_ID:398,
	JOB_CUSTOMER_SERVICE_CATEGORY_ID:386,
	JOB_PUBLIC_RELATIONS_CATEGORY_ID: 413,
	JOB_ART_ENTERTAINMENT_CATEGORY_ID:378,
	JOB_EXECUTIVE_CATEGORY_ID:392,
	JOB_GRAPHIC_DESIGN_CATEGORY_ID:396,
});
//searching type for listing.html
hashiApp.constant('searchTypeConstant', {
	BASIC_SEARCH: 'BasicSearch',
	ADVANCE_SEARCH: 'AdvanceSearch',
	ADVANCE_SEARCH_CAR: 'AdvanceSearchCar',
	ADVANCE_SEARCH_CAR_PARTS: 'AdvanceSearchCarParts',
	ADVANCE_SEARCH_BOATS: 'AdvanceSearchBoats',
	ADVANCE_SEARCH_HEAVY_VEHICLE: 'AdvanceSearchHeavyVehicle',
	ADVANCE_SEARCH_MOTORCYCLE: 'AdvanceSearchMotorcycle',
	ADVANCE_SEARCH_SALE_RES: 'AdvanceSearchSaleRes',
	ADVANCE_SEARCH_SALE_COMM: 'AdvanceSearchSaleComm',
	ADVANCE_SEARCH_SALE_UNITS: 'AdvanceSearchSaleUnits',
	ADVANCE_SEARCH_SALE_LAND: 'AdvanceSearchSaleLand',
	ADVANCE_SEARCH_RENT_RES: 'AdvanceSearchRentRes',
	ADVANCE_SEARCH_RENT_COMM: 'AdvanceSearchRentComm',
	ADVANCE_SEARCH_RENT_ROOM: 'AdvanceSearchRentRoom',
	ADVANCE_SEARCH_RENT_SHORT_DAILY: 'AdvanceSearchRentShortDaily',
	ADVANCE_SEARCH_RENT_SHORT_MONTHLY: 'AdvanceSearchRentShortMonthly',
	ADVANCE_SEARCH_CLASSIFIED : 'AdvanceSearchClassified',
	ADVANCE_SEARCH_JOB : 'AdvanceSearchJob',
	ADVANCE_SEARCH_COMMUNITY : 'AdvanceSearchCommunity'
});

//colours in English for post_ad_car


hashiApp.constant('coloursEnglish',  [       
                                               {"id":1, "name":"White"},
                                               {"id":2,  "name":"Black"},
                                               {"id":3,  "name":"Green"},
                                               {"id":4,  "name":"Blue"},
                                               {"id":5,  "name":"Yellow"},
                                               {"id":6,  "name":"Red"},
                                               {"id":7,  "name":"Pink"},
                                               {"id":8,  "name":"Silver"},
                                               {"id":9,  "name":"Other colour"}
]); 


hashiApp.constant('coloursSomali',  [       
                                      {"id":1, "name":"Cadaan"},
                                      {"id":2,  "name":"Madoow"},
                                      {"id":3,  "name":"Cagaar"},
                                      {"id":4,  "name":"Buluug"},
                                      {"id":5,  "name":"Jaale"},
                                      {"id":6,  "name":"Guduud"},
                                      {"id":7,  "name":"Basali"},
                                      {"id":8,  "name":"Qalin"},
                                      {"id":9,  "name":"Midab kale"}
]); 


//body type car 

hashiApp.constant('bodyTypeCarEnglish',  [       
                                     {"id":1,  "name":"Hatchback"},
                                     {"id":2,  "name":"Sedan"},
                                     {"id":3,  "name":"MUV/SUV"},
                                     {"id":4,  "name":"Coupe"},
                                     {"id":5,  "name":"Convertible"},
                                     {"id":6,  "name":"Wagon"},
                                     {"id":7,  "name":"Van"},
                                     {"id":8,  "name":"Jeep"},
                                     {"id":9,  "name":"Other"}
                                     
                                     
]); 

hashiApp.constant('bodyTypeCarSomali',  [       
                                     {"id":1,  "name":"Hatchback"},
                                     {"id":2,  "name":"Sedan"},
                                     {"id":3,  "name":"MUV/SUV"},
                                     {"id":4,  "name":"Coupe"},
                                     {"id":5,  "name":"Convertible"},
                                     {"id":6,  "name":"Wagon"},
                                     {"id":7,  "name":"Van"},
                                     {"id":8,  "name":"Jeep"},
                                     {"id":9,  "name":"Other"}
]); 



//car fuel type  
hashiApp.constant('fuelTypeCarEnglish',  [       
                                         {"id":1,  "name":"Petrol"},
                                         {"id":2,  "name":"Diesel"},
                                         {"id":3,  "name":"Hybrid"},
                                         {"id":4,  "name":"Electric"}
                                         
]); 

hashiApp.constant('fuelTypeCarSomali',  [       
                                              {"id":1,  "name":"Petrol"},
                                              {"id":2,  "name":"Naafto"},
                                              {"id":3,  "name":"Hybrid"},
                                              {"id":4,  "name":"Koronto"}
]); 


//car transmission type  

hashiApp.constant('transmissionCarEnglish',  [       
                                             {"id":1,  "name":"Manual"},
                                             {"id":2,  "name":"Automatic"}
                                             
  ]); 

hashiApp.constant('transmissionCarSomali',  [       
                                            {"id":1,  "name":"Manual"},
                                            {"id":2,  "name":"Automatic"}                                        
 ]); 


//seller type for car 

hashiApp.constant('sellerTypeEnglish',  [       
                                           {"id":1,  "name":"Owner"},
                                           {"id":2,  "name":"Dealer"}
                                           
]); 

hashiApp.constant('sellerTypeSomali',  [       
                                               {"id":1,  "name":"Miilkiile"},
                                               {"id":2,  "name":"Ganacsade"}
                                               
 ]); 



//boats variable 
hashiApp.constant('lengthEnglish',  [       
                                               {"id":1, "name":"Under 10 ft."},
                                               {"id":2, "name":"10-14 ft."},
                                               {"id":3, "name":"15-19 ft."},
                                               {"id":4, "name":"20-24 ft."},
                                               {"id":5, "name":"25-29 ft."},
                                               {"id":6, "name":"30-39 ft."},
                                               {"id":7, "name":"40-49 ft."},
                                               {"id":8, "name":"50-69 ft."},
                                               {"id":9, "name":"70-100 ft."},
                                               {"id":10, "name":"100+ ft."}

                                               
]);

hashiApp.constant('lengthSomali',  [       
                                     {"id":1, "name":"Under 10 tilaabo."},
                                     {"id":2, "name":"10-14 tilaabo."},
                                     {"id":3, "name":"15-19 ft."},
                                     {"id":4, "name":"20-24 ft."},
                                     {"id":5, "name":"25-29 ft."},
                                     {"id":6, "name":"30-39 ft."},
                                     {"id":7, "name":"40-49 ft."},
                                     {"id":8, "name":"50-69 ft."},
                                     {"id":9, "name":"70-100 ft."},
                                     {"id":10, "name":"100+ ft."}

                                     
]);


hashiApp.constant('warrantyEnglish',  [       
                                               {"id":1, "name":"Yes"},
                                               {"id":2,  "name":"No"},
                                               {"id":3,  "name":"Does not apply"}                                             
]); 
hashiApp.constant('warrantySomali',  [       
                                               {"id":1, "name":"Haa"},
                                               {"id":2,  "name":"May"},
                                               {"id":3,  "name":"Ma khuseeyo"}                                               
]); 

//heavy vechicles

hashiApp.constant('noCylindersEnglish',  [       
                                      {"id":1, "name":3},
                                      {"id":2,  "name":4},
                                      {"id":3,  "name":5},
                                      {"id":4,  "name":6},
                                      {"id":5,  "name":7},
                                      {"id":6,  "name":8},
                                      {"id":7,  "name":9},
                                      {"id":8,  "name":10},
                                      {"id":9,  "name":"12+"},
                                      {"id":10,  "name":"Unknown"}
                                      
]); 
hashiApp.constant('noCylindersSomali',  [       
                                          {"id":1, "name":3},
                                          {"id":2,  "name":4},
                                          {"id":3,  "name":5},
                                          {"id":4,  "name":6},
                                          {"id":5,  "name":7},
                                          {"id":6,  "name":8},
                                          {"id":7,  "name":9},
                                          {"id":8,  "name":10},
                                          {"id":9,  "name": "12+"},
                                          {"id":10,  "name":"Magaranayo"}
]); 


hashiApp.constant('horsePowerEnglish',  [       
                                        {"id":1, "name":"Less than 150 HP"},
                                        {"id":2,  "name":"150 - 200 HP"},
                                        {"id":3,  "name":"200 - 300 HP"},
                                        {"id":4,  "name":"300 - 400 HP"},
                                        {"id":5,  "name":"400+ HP"},
                                        {"id":6,  "name":"Unknown"}
]); 
hashiApp.constant('horsePowerSomali',  [       
                                         {"id":1, "name":"Less than 150 HP"},
                                         {"id":2,  "name":"150 - 200 HP"},
                                         {"id":3,  "name":"200 - 300 HP"},
                                         {"id":4,  "name":"300 - 400 HP"},
                                         {"id":5,  "name":"400+ HP"},
                                         {"id":6,  "name":"Magaranayo"}
]); 


//property for sale fields

hashiApp.constant('noBedroomsVillaEnglish',  [       
                                      {"id":1,  "name":"1"},
                                      {"id":2,  "name":"2"},
                                      {"id":3,  "name":"3"},
                                      {"id":4,  "name":"4"},
                                      {"id":5,  "name":"5"},
                                      {"id":6,  "name":"6"},
                                      {"id":7,  "name":"7"},
                                      {"id":8,  "name":"8+"}
]); 

hashiApp.constant('noBedroomsVillaSomali',  [       
                                              {"id":1,  "name":"1"},
                                              {"id":2,  "name":"2"},
                                              {"id":3,  "name":"3"},
                                              {"id":4,  "name":"4"},
                                              {"id":5,  "name":"5"},
                                              {"id":6,  "name":"6"},
                                              {"id":7,  "name":"7"},
                                              {"id":8,  "name":"8+"}
]); 

hashiApp.constant('noBedroomsApartmentEnglish',  [       
                                         {"id":1, "name":"Studio"},
                                         {"id":2,  "name":"1"},
                                         {"id":3,  "name":"2"},
                                         {"id":4,  "name":"3"},
                                         {"id":5,  "name":"4"},
                                         {"id":6,  "name":"5"},
                                         {"id":7,  "name":"6"},
                                         {"id":8,  "name":"7"},
                                         {"id":9,  "name":"8+"}
]); 

hashiApp.constant('noBedroomsApartmentSomali',  [       
                                            {"id":1, "name":"Studio"},
                                            {"id":2,  "name":"1"},
                                            {"id":3,  "name":"2"},
                                            {"id":4,  "name":"3"},
                                            {"id":5,  "name":"4"},
                                            {"id":6,  "name":"5"},
                                            {"id":7,  "name":"6"},
                                            {"id":8,  "name":"7"},
                                            {"id":9,  "name":"8+"}
]); 

hashiApp.constant('noBathroomsEnglish',  [       
                                                 {"id":1,  "name":"1"},
                                                 {"id":2,  "name":"2"},
                                                 {"id":3,  "name":"3"},
                                                 {"id":4,  "name":"4"},
                                                 {"id":5,  "name":"5"},
                                                 {"id":6,  "name":"6"},
                                                 {"id":7,  "name":"7"},
                                                 {"id":8,  "name":"8"}
]); 

hashiApp.constant('noBathroomsSomali',  [       
                                          {"id":1,  "name":"1"},
                                          {"id":2,  "name":"2"},
                                          {"id":3,  "name":"3"},
                                          {"id":4,  "name":"4"},
                                          {"id":5,  "name":"5"},
                                          {"id":6,  "name":"6"},
                                          {"id":7,  "name":"7"},
                                          {"id":8,  "name":"8"}
]); 


hashiApp.constant('ownerTypeEnglish',  [       
                                               {"id":1, "name":"Landloard"},
                                               {"id":2,  "name":"Agent"},
]); 

hashiApp.constant('ownerTypeSomali',  [       
                                        {"id":1, "name":"Milkiile"},
                                        {"id":2,  "name":"Dalaal"},
]); 


//Land for sale
hashiApp.constant('zonedForEnglish',  [       
                                              {"id":1, "name":"Residential"},
                                              {"id":2,  "name":"Commercial"},
                                              {"id":3,  "name":"Retail"},
                                              {"id":4,  "name":"Mixed Use"}  
]); 

hashiApp.constant('zonedForSomali',  [       
                                       {"id":1, "name":"Deegaan"},
                                       {"id":2,  "name":"Ganacsi"},
                                       {"id":3,  "name":"Dukaamo"},
                                       {"id":4,  "name":"Isku jir"}  
]); 



//for rent residential 
hashiApp.constant('rentTimeEnglish',  [       
                                      {"id":1, "name":"Daily"},
                                      {"id":2,  "name":"Weekly"},
                                      {"id":3,  "name":"Monthly"},
                                      {"id":4,  "name":"Yearly"},
                                      {"id":5,  "name":"Negotiable"},
]); 

//for rent residential 
hashiApp.constant('rentTimeSomali',  [       
                                      {"id":1, "name":"Maalin"},
                                      {"id":2,  "name":"Usbuuc"},
                                      {"id":3,  "name":"Bishi"},
                                      {"id":4,  "name":"Sanadki"},
                                      {"id":5,  "name":"Gorgortan"},
]); 

hashiApp.constant('furnishedEnglish',  [       
                                      {"id":1, "name":"Furnished"},
                                      {"id":2,  "name":"Unfurnished"}
                                     
]); 

hashiApp.constant('furnishedSomali',  [       
                                         {"id":1, "name":"Wuu goglanyahay"},
                                         {"id":2,  "name":"Ma goglana"}
]); 

//classified items
hashiApp.constant('conditionEnglish',  [       
                       
                                      {"id":1,  "name":"Excellent"},
                                      {"id":2,  "name":"Good"},
                                      {"id":3,  "name":"Average"},
                                      {"id":4,  "name":"Poor"},
]); 
hashiApp.constant('conditionSomali',  [       
                                        {"id":1, "name":"Heer sare"},
                                        {"id":2,  "name":"Wanaagsan"},
                                        {"id":3,  "name":"Dhexdhexaad"},
                                        {"id":4,  "name":"Wow liitaa"}
                                       
]); 

hashiApp.constant('ageEnglish',  [       
                                        {"id":1, "name":"New"},
                                        {"id":2,  "name":"Used"}               
]); 
hashiApp.constant('ageSomali',  [       
                                    {"id":1, "name":"Cuseeb"},
                                    {"id":2,  "name":"Waa la'isticmaalay"}
                                 
]);  

hashiApp.constant('cameraBrand',  [       
                                    {"id":1, "name":"Aiptek"},
                                    {"id":2,  "name":"Canon"},
                                    {"id":3,  "name":"Casio"},
                                    {"id":4,  "name":"Dual"},
                                    {"id":5,  "name":"Fujifilm"},
                                    {"id":6, "name":"HP"},
                                    {"id":7,  "name":"JVC"},
                                    {"id":8,  "name":"Kodak"},
                                    {"id":9,  "name":"Konika"},
                                    {"id":10,  "name":"Konika Minolta"},
                                    {"id":11, "name":"Leica"},
                                    {"id":12,  "name":"Minolta"},
                                    {"id":13,  "name":"Nikon"},
                                    {"id":14,  "name":"Olympus"},
                                    {"id":15,  "name":"Panasonic"},
                                    {"id":16, "name":"Pentax"},
                                    {"id":17,  "name":"Polaroid"},
                                    {"id":18,  "name":"Pure Digital"},
                                    {"id":19,  "name":"Samsung"},
                                    {"id":20,  "name":"Sanyo"},
                                    {"id":21,  "name":"Sharp"},
                                    {"id":22,  "name":"Sigma"},
                                    {"id":23,  "name":"Sony"},
                                    {"id":24,  "name":"SVP"},
                                    {"id":25,  "name":"Vivitar"},
                                    {"id":26,  "name":"Other Brand"}
]); 

// computer stuff
hashiApp.constant('computerBrand',  [       
                                   {"id":1, "name":"Acer"},
                                   {"id":2,  "name":"Adobe"},
                                   {"id":3,  "name":"Alienware"},
                                   {"id":4,  "name":"AMD"},
                                   {"id":5,  "name":"Apple/Mac"},
                                   {"id":6, "name":"ASUS"},
                                   {"id":7,  "name":"ATI"},
                                   {"id":8,  "name":"Belkin"},
                                   {"id":9,  "name":"BENQ"},
                                   {"id":10,  "name":"Brother"},
                                   {"id":11, "name":"Canon"},
                                   {"id":12,  "name":"Compaq"},
                                   {"id":13,  "name":"D-Link"},
                                   {"id":14,  "name":"Dell"},
                                   {"id":15,  "name":"eMachines"},
                                   {"id":16, "name":"Fujitsu"},
                                   {"id":17,  "name":"Gateway"},
                                   {"id":18,  "name":"HP"},
                                   {"id":19,  "name":"IBM/Lenovo"},
                                   {"id":20,  "name":"Intel"},
                                   {"id":21,  "name":"Lexmark"},
                                   {"id":22,  "name":"Linksys"},
                                   {"id":23,  "name":"Logitech"},
                                   {"id":24,  "name":"Macromedia"},
                                   {"id":25,  "name":"Microsoft"},
                                   {"id":26,  "name":"NEC"},
                                   {"id":27, "name":"OKI"},
                                   {"id":28,  "name":"Packard Bell"},
                                   {"id":29,  "name":"Panasonic"},
                                   {"id":30,  "name":"Samsung"},
                                   {"id":31,  "name":"Sony"},
                                   {"id":32, "name":"Toshiba"},
                                   {"id":33,  "name":"Western Digital"},
                                   {"id":34,  "name":"Xerox"},
                                   {"id":35,  "name":"Other"}
]); 

hashiApp.constant('computerMemory',  [       
                                   {"id":1, "name":"512 MB or more"},
                                   {"id":2,  "name":"1 GB or more"},
                                   {"id":3,  "name":"2 GB or more"},
                                   {"id":4,  "name":"4 GB or more"},
                                   {"id":5,  "name":"8 GB or more"}
]); 
hashiApp.constant('computerProcessor',  [       
                                      {"id":1, "name":"Less than 500 MHz"},
                                      {"id":2,  "name":"500 MHz or more"},
                                      {"id":3,  "name":"1 GHz or more"},
                                      {"id":4,  "name":"2 GHz or more"} 
]); 
hashiApp.constant('computerHardDrive',  [       
                                         {"id":1, "name":"0 - 99 GB"},
                                         {"id":2,  "name":"100 - 249 GB"},
                                         {"id":3,  "name":"250 - 499 GB"},
                                         {"id":4,  "name":"500 - 749 GB"},
                                         {"id":5,  "name":"750 - 999 GB"},
                                         {"id":6,  "name":"1 - 1.49 TB"},
                                         {"id":7,  "name":"1.5 - 1.9 TB"},
                                         {"id":8,  "name":"2+ TB"} 
   ]); 

//job stuff
hashiApp.constant('careerLevelEnglish',  [       
                                 {"id":1, "name":"Student/Intern"},
                                 {"id":2,  "name":"Junior"},
                                 {"id":3,  "name":"Mid-level"},
                                 {"id":4,  "name":"Senior"},
                                 {"id":5,  "name":"Manager"},
                                 {"id":6,  "name":"Executive/Director"},
                                 {"id":7,  "name":"Other"}
]);

hashiApp.constant('careerLevelSomali',  [       
                                          {"id":1, "name":"Arday/Intern"},
                                          {"id":2,  "name":"Junior"},
                                          {"id":3,  "name":"Maamul dhexe"},
                                          {"id":4,  "name":"Maamul sare"},
                                          {"id":5,  "name":"Maamule"},
                                          {"id":6,  "name":"Agaasime"},
                                          {"id":7,  "name":"Wax kale"}
]);

hashiApp.constant('employmentTypeEnglish',  [       
                                          {"id":1, "name":"Full Time"},
                                          {"id":2,  "name":"Part Time"},
                                          {"id":3,  "name":"Contract"},
                                          {"id":4,  "name":"Temporary"},
                                          {"id":5,  "name":"Other"}
]);
hashiApp.constant('employmentTypeSomali',  [       
                                             {"id":1, "name":"Waqti buuxa"},
                                             {"id":2,  "name":"Waqti dhiman"},
                                             {"id":3,  "name":"Heshiis"},
                                             {"id":4,  "name":"Waqti go'an"},
                                             {"id":5,  "name":"Wax kale"}
]);


hashiApp.constant('workExperienceEnglish',  [       
                                            {"id":1, "name":"0-1 Years"},
                                            {"id":2,  "name":"1-2 Years"},
                                            {"id":3,  "name":"2-5 Years"},
                                            {"id":4,  "name":"5-10 Years"},
                                            {"id":5,  "name":"10-15 Years"},
                                            {"id":6,  "name":"15+ Years"}                                         
]);

hashiApp.constant('workExperienceSomali',  [       
                                             {"id":1, "name":"0-1 Sano"},
                                             {"id":2,  "name":"1-2 Sano"},
                                             {"id":3,  "name":"2-5 Sano"},
                                             {"id":4,  "name":"5-10 Sano"},
                                             {"id":5,  "name":"10-15 Sano"},
                                             {"id":6,  "name":"15+ Sano"}                                         
 ]);

hashiApp.constant('educationLevelEnglish',  [       
                                            {"id":1, "name":"Secondary"},
                                            {"id":2, "name":"High-School"},
                                            {"id":3,  "name":"Bachelors Degree"},
                                            {"id":4,  "name":"Masters Degree"},
                                            {"id":5,  "name":"PhD"},
                                            {"id":6,  "name":"10-15 Years"},
                                            {"id":7,  "name":"Not needed"}                                         
]);

hashiApp.constant('educationLevelSomali',  [       
                                             {"id":1, "name":"Dugsi Dhexe"},
                                             {"id":2, "name":"Dugsi Sare"},
                                             {"id":3,  "name":"Jaamacad degree"},
                                             {"id":4,  "name":"Master Degree"},
                                             {"id":5,  "name":"PhD"},
                                             {"id":6,  "name":"10-15 Years"},
                                             {"id":7,  "name":"Maloo baahno"}                                         
 ]);


hashiApp.constant('monthSalaryEnglish',  [       
                                             {"id":1, "name":"Unspecified"},
                                             {"id":2,  "name":"0      -  100"},
                                             {"id":3,  "name":"100    -  200"},
                                             {"id":4,  "name":"200    -  400"},
                                             {"id":5,  "name":"400    -  600"},
                                             {"id":6,  "name":"600    -  800"},
                                             {"id":7,  "name":"800    -  1,000"},
                                             {"id":8,  "name":"1,000  -  1,500"},  
                                             {"id":9,  "name":"1,500  -  1,500"},
                                             {"id":10,  "name":"1,500 -  2,000"},
                                             {"id":11,  "name":"2,000 -  3,000"},
                                             {"id":12,  "name":"3,000 -  5,000"},
                                             {"id":13,  "name":"5,000 -  7,000"},
                                             {"id":14,  "name":"9,000 -  10,000"},
                                             {"id":15,  "name":"10,000+"}
 ]);
hashiApp.constant('monthSalarySomali',  [       
                                          {"id":1, "name":"Unspecified"},
                                          {"id":2,  "name":"0      -  100"},
                                          {"id":3,  "name":"100    -  200"},
                                          {"id":4,  "name":"200    -  400"},
                                          {"id":5,  "name":"400    -  600"},
                                          {"id":6,  "name":"600    -  800"},
                                          {"id":7,  "name":"800    -  1,000"},
                                          {"id":8,  "name":"1,000  -  1,500"},  
                                          {"id":9,  "name":"1,500  -  1,500"},
                                          {"id":10,  "name":"1,500 -  2,000"},
                                          {"id":11,  "name":"2,000 -  3,000"},
                                          {"id":12,  "name":"3,000 -  5,000"},
                                          {"id":13,  "name":"5,000 -  7,000"},
                                          {"id":14,  "name":"9,000 -  10,000"},
                                          {"id":15,  "name":"10,000+"}
]);



hashiApp.constant('companySizeEnglish',  [       
                                         {"id":1,  "name":"1-10"},
                                         {"id":2,  "name":"11-50"},
                                         {"id":3,  "name":"51-200"},
                                         {"id":4,  "name":"201-500"},
                                         {"id":5,  "name":"501-1000"},
                                         {"id":6,  "name":"1001-5000"},
                                         {"id":7,  "name":"5001-10000"},
                                         {"id":8,  "name":"10000+"}
]);

hashiApp.constant('companySizeSomali',  [       
                                          {"id":1,  "name":"1-10"},
                                          {"id":2,  "name":"11-50"},
                                          {"id":3,  "name":"51-200"},
                                          {"id":4,  "name":"201-500"},
                                          {"id":5,  "name":"501-1000"},
                                          {"id":6,  "name":"1001-5000"},
                                          {"id":7,  "name":"5001-10000"},
                                          {"id":8,  "name":"10000+"}
 ]);


hashiApp.constant('cvRequiredEnglish',  [       
                                               {"id":1,  "name":"Yes"},
                                               {"id":2,  "name":"No"}                                          
]); 
hashiApp.constant('cvRequiredSomali',  [       
                                         {"id":1,  "name":"Haa"},
                                         {"id":2,  "name":"May"}                                          
]); 

hashiApp.constant('listedByEnglish',  [       
                                        {"id":1,  "name":"Employer"},
                                        {"id":2,  "name":"Recruiter"}                                          
]);

hashiApp.constant('listedBySomali',  [       
                                       {"id":1,  "name":"Loo-shaqeeye"},
                                       {"id":2,  "name":"Qore"}                                          
]);

hashiApp.constant('hideCompanyNameSomali',  [       
                                      {"id":1,  "name":"Haa"},
                                      {"id":2,  "name":"May"}                                          
]);

hashiApp.constant('hideCompanyNameEnglish',  [       
                                             {"id":1,  "name":"Yes"},
                                             {"id":2,  "name":"No"}                                          
]);


//seller type for the search page listing.html  
hashiApp.constant('sellerTypeSearchEnglish',  [       
                                           {"id":0, "name":"All Type"},
                                           {"id":1,  "name":"Owner"},
                                           {"id":2,  "name":"Dealer"}                                          
]); 

hashiApp.constant('sellerTypeSearchSomali',  [       
                                               {"id":0, "name":"Kuli"},
                                               {"id":1,  "name":"Milkiilaha"},
                                               {"id":2,  "name":"Ganacsade"}                                
]); 




//error code

hashiApp.constant('errorsConstant', {
	ERROR01: 'Picture is too big',
	ERROR02: '***'
});