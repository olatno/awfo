package com.ecommerce.awf.repository.loader;

import com.ecommerce.awf.enums.DiscountType;
import com.ecommerce.awf.enums.ProductWeightBase;
import com.ecommerce.awf.model.admin.Image;
import com.ecommerce.awf.model.admin.PromotionType;
import com.ecommerce.awf.model.commerce.*;
import com.ecommerce.awf.repository.admin.ImageRepository;
import com.ecommerce.awf.repository.commerce.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductCodeRepository productCodeRepository;
    private final PromotionRepository promotionRepository;
    private final PriceRepository priceRepository;
    private final GalleryRepository galleryRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductWeightRepository productWeightRepository;
    private final WeightListRepository weightListRepository;

    @Autowired
    public DatabaseLoader(ProductRepository productRepository, ProductCodeRepository productCodeRepository, PromotionRepository promotionRepository,
                          PriceRepository priceRepository, GalleryRepository galleryRepository, ImageRepository imageRepository,
                          CategoryRepository categoryRepository, BrandRepository brandRepository, ProductGroupRepository productGroupRepository,
                          ProductWeightRepository productWeightRepository, WeightListRepository weightListRepository) {
        this.productRepository = productRepository;
        this.productCodeRepository = productCodeRepository;
        this.promotionRepository = promotionRepository;
        this.priceRepository = priceRepository;
        this.galleryRepository = galleryRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productGroupRepository = productGroupRepository;
        this.productWeightRepository = productWeightRepository;
        this.weightListRepository = weightListRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CommandLineRunner running in the DatabaseLoader class...");
        //Brand
        final Brand foc = Brand.builder().name("FOC Foods").logoPath("/home/olatno/Pictures/brand").build();
        final Brand medvacc = Brand.builder().name("Medvacc").logoPath("/home/olatno/Pictures/brand").build();
        final Brand ayoola = Brand.builder().name("Ayoola").logoPath("/home/olatno/Pictures/brand").build();
        final Brand afrimash = Brand.builder().name("Afrimash").logoPath("/home/olatno/Pictures/brand").build();
        Stream.of(foc,medvacc,ayoola,afrimash).forEach(brandRepository::save);

        //Promotion
        final PromotionType promotionType = PromotionType.builder().value(0).type(DiscountType.FLAT).build();
        final PromotionType promotionType1 = PromotionType.builder().value(10).type(DiscountType.PERCENTAGE).build();
        final PromotionType promotionType2 = PromotionType.builder().value(10).type(DiscountType.FLAT).build();
        final Promotion promotion = Promotion.builder().name("coupon code").description("first shopper").created(LocalDate.now()).promotionType(null).build();
        final Promotion promotion1 = Promotion.builder().name("coupon code").description("first shopper").created(LocalDate.now()).promotionType(null).build();
        final Promotion promotion2 = Promotion.builder().name("coupon code").description("first shopper").created(LocalDate.now()).promotionType(null).build();
        this.promotionRepository.save(promotion);
        this.promotionRepository.save(promotion1);
        this.promotionRepository.save(promotion2);

        //Price
        final Price price = Price.builder().promotion(null).value(new BigDecimal(10.00)).build();
        final Price price1 = Price.builder().promotion(null).value(new BigDecimal(20.00)).build();
        final Price price2 = Price.builder().promotion(null).value(new BigDecimal(30.00)).build();
        final Price price3 = Price.builder().promotion(null).value(new BigDecimal(40.00)).build();
        final Price price4 = Price.builder().promotion(null).value(new BigDecimal(50.00)).build();
        final Price price5 = Price.builder().promotion(null).value(new BigDecimal(60.00)).build();
        Stream.of(price,price1,price2,price3,price4,price5).forEach(priceRepository::save);

        //Gallery yam
        final Gallery cocoYamGallery = Gallery.builder().created(LocalDate.now()).description("Coco yam all images").name("Coco yam").build();
        final Gallery whiteYamGallery = Gallery.builder().created(LocalDate.now()).description("White yam all images").name("White yam").build();
        final Gallery ewuraYamGallery = Gallery.builder().created(LocalDate.now()).description("Ewura yam all images").name("Ewura yam").build();
        final Gallery cassavaYamGallery = Gallery.builder().created(LocalDate.now()).description("Cassava yam all images").name("Cassava yam").build();
        Stream.of(cocoYamGallery,whiteYamGallery,ewuraYamGallery,cassavaYamGallery).forEach(galleryRepository::save);

        //Gallery gari
        final Gallery gariWhiteGallery = Gallery.builder().created(LocalDate.now()).description("Gari white all images").name("Gari white").build();
        final Gallery gariIjebuGallery = Gallery.builder().created(LocalDate.now()).description("Gari ijebu all images").name("Gari ijebu").build();
        final Gallery gariYellowGallery = Gallery.builder().created(LocalDate.now()).description("Gari yellow all images").name("Gari yellow").build();
        Stream.of(gariWhiteGallery, gariIjebuGallery, gariYellowGallery).forEach(galleryRepository::save);

        //Gallery flour
        final Gallery flourCassavaGallery = Gallery.builder().created(LocalDate.now()).description("Flour cassava all images").name("Flour cassava images").build();
        final Gallery flourYamGallery = Gallery.builder().created(LocalDate.now()).description("Flour yam all images").name("Flour yam images").build();
        final Gallery flourPlantainGallery = Gallery.builder().created(LocalDate.now()).description("Flour plantain all images").name("Flour plantain images").build();
        Stream.of(flourCassavaGallery,flourYamGallery,flourPlantainGallery).forEach(galleryRepository::save);

        //Gallery dry vegetable
        final Gallery bitterLeaveGallery = Gallery.builder().created(LocalDate.now()).description("Bitter leave all images").name("Bitter leave images").build();
        final Gallery egusiGroundedGallery = Gallery.builder().created(LocalDate.now()).description("Egusi grounded all images").name("Egusi grounded images").build();
        final Gallery egusiSeedGallery = Gallery.builder().created(LocalDate.now()).description("Egusi seed all images").name("Egusi seed images").build();
        final Gallery ishapaGallery = Gallery.builder().created(LocalDate.now()).description("Ishapa all images").name("Ishapa images").build();
        final Gallery mushroomsGallery = Gallery.builder().created(LocalDate.now()).description("Mushrooms all images").name("Mushrooms images").build();
        final Gallery ogbonoGroundedGallery = Gallery.builder().created(LocalDate.now()).description("Ogbono grounded all images").name("Ogbono grounded images").build();
        final Gallery ogbonoSeedGallery = Gallery.builder().created(LocalDate.now()).description("Ogbono seed all images").name("Ogbono seed images").build();
        final Gallery okaziLeaveGallery = Gallery.builder().created(LocalDate.now()).description("Okazi leave all images").name("Okazi leave images").build();
        Stream.of(bitterLeaveGallery, egusiGroundedGallery, egusiSeedGallery, ishapaGallery, mushroomsGallery, ogbonoGroundedGallery, ogbonoSeedGallery, okaziLeaveGallery).forEach(galleryRepository::save);

        //Gallery spices
        final Gallery curryGallery = Gallery.builder().created(LocalDate.now()).description("Curry all images").name("Curry images").build();
        final Gallery locustBeansGallery = Gallery.builder().created(LocalDate.now()).description("Locust Beans all images").name("Locust Beans images").build();
        final Gallery pepperGallery = Gallery.builder().created(LocalDate.now()).description("Pepper all images").name("Pepper images").build();
        Stream.of(curryGallery,locustBeansGallery,pepperGallery).forEach(galleryRepository::save);

        //Gallery dry fish
        final Gallery catFishGallery = Gallery.builder().created(LocalDate.now()).description("Cat fish all images").name("Cat fish images").build();
        final Gallery crayfishGallery = Gallery.builder().created(LocalDate.now()).description("Cray fish all images").name("Cray fish images").build();
        final Gallery herringGallery = Gallery.builder().created(LocalDate.now()).description("Herring all images").name("Herring images").build();
        final Gallery oysterGallery = Gallery.builder().created(LocalDate.now()).description("Oyster all images").name("Oyster images").build();
        Stream.of(catFishGallery,crayfishGallery,herringGallery,oysterGallery).forEach(galleryRepository::save);

        //Dry vegetable leaves images
        //Bitter leave images
        final Image bitterLeaveOpen = Image.builder().created(LocalDate.now()).description("Open bitter leave").gallery(bitterLeaveGallery).name("Open bitter leave").path("/images/gallery/dry-vegetable/bitter-leave/open-bitter-leaf.jpeg").build();
        final Image bitterLeavePackaged = Image.builder().created(LocalDate.now()).description("Packaged bitter ijebu").gallery(bitterLeaveGallery).name("Packaged bitter ijebu").path("/images/gallery/dry-vegetable/bitter-leave/packaged-bitter-leave.png").build();
        //Egusi grounded images
        final Image egusiGroundedOpen = Image.builder().created(LocalDate.now()).description("Open egusi grounded").gallery(egusiGroundedGallery).name("Open egusi grounded").path("/images/gallery/dry-vegetable/egusi-grounded/open-egusi.jpg").build();
        final Image egusiGroundedPackaged = Image.builder().created(LocalDate.now()).description("Packaged egusi grounded").gallery(egusiGroundedGallery).name("Packaged egusi grounded").path("/images/gallery/dry-vegetable/egusi-grounded/packaged-egusi.jpeg").build();
        //Egusi seed images
        final Image egusiSeedOpen = Image.builder().created(LocalDate.now()).description("Open egusi seed").gallery(egusiSeedGallery).name("Open egusi seed").path("/images/gallery/dry-vegetable/egusi-seed/open-egusi.png").build();
        final Image egusiSeedPackaged = Image.builder().created(LocalDate.now()).description("Packaged egusi seed").gallery(egusiSeedGallery).name("Packaged egusi seed").path("/images/gallery/dry-vegetable/egusi-seed/packaged-egusi.png").build();
        //Ishapa image
        final Image ishapaOpen = Image.builder().created(LocalDate.now()).description("Open ishapa").gallery(ishapaGallery).name("Open ishapa").path("/images/gallery/dry-vegetable/ishapa/open-zobo-leaves.jpg").build();
        //Mushroom images
        final Image mushroomsOpen = Image.builder().created(LocalDate.now()).description("Open mushrooms").gallery(mushroomsGallery).name("Open mushrooms").path("/images/gallery/dry-vegetable/mushrooms/open-mushrooms.jpg").build();
        final Image mushroomsPackaged = Image.builder().created(LocalDate.now()).description("Packaged mushrooms").gallery(mushroomsGallery).name("Packaged mushrooms").path("/images/gallery/dry-vegetable/mushrooms/packaged-mushrooms.jpg").build();
        //Ogbono seed grounded images
        final Image ogbonoGroundedOpen = Image.builder().created(LocalDate.now()).description("Open ogbono grounded").gallery(ogbonoGroundedGallery).name("Open ogbono grounded").path("/images/gallery/dry-vegetable/ogbono-grounded/open-ogbono.jpg").build();
        final Image ogbonoGroundedPackaged = Image.builder().created(LocalDate.now()).description("Packaged ogbono grounded").gallery(ogbonoGroundedGallery).name("Packaged ogbono grounded").path("/images/gallery/dry-vegetable/ogbono-grounded/packaged-ogbono.png").build();
        //Ogbono seed open images
        final Image ogbonoSeedOpen = Image.builder().created(LocalDate.now()).description("Open ogbono seed").gallery(ogbonoSeedGallery).name("Open ogbono seed").path("/images/gallery/dry-vegetable/ogbono-seed/open-ogbono.jpg").build();
        final Image ogbonoSeedPackaged = Image.builder().created(LocalDate.now()).description("Packaged ogbono seed").gallery(ogbonoSeedGallery).name("Packaged ogbono seed").path("/images/gallery/dry-vegetable/ogbono-seed/packaged-ogbono.png").build();
        //okazi leave images
        final Image okaziLeaveOpen = Image.builder().created(LocalDate.now()).description("Open okazi leave").gallery(okaziLeaveGallery).name("Open okazi leave").path("/images/gallery/dry-vegetable/okazi-leave/open-okazi-leaf.jpg").build();
        final Image okaziLeavePackaged = Image.builder().created(LocalDate.now()).description("Packaged okazi leave").gallery(okaziLeaveGallery).name("Packaged okazi leave").path("/images/gallery/dry-vegetable/okazi-leave/packaged-okazi.png").build();
        Stream.of(bitterLeaveOpen,bitterLeavePackaged,egusiGroundedOpen,egusiGroundedPackaged,egusiSeedOpen,egusiSeedPackaged,ishapaOpen,mushroomsOpen,mushroomsPackaged,ogbonoGroundedOpen,
                ogbonoGroundedPackaged,ogbonoSeedOpen,ogbonoSeedPackaged,okaziLeaveOpen,okaziLeavePackaged).forEach(imageRepository::save);

        //Yams images
        final Image cocoyamimage = Image.builder().created(LocalDate.now()).description("Coco yam thumbnail").gallery(cocoYamGallery).name("Coco yam thumbnail").path("/images/gallery/yam/coco-yam/coco-yam-open.jpg").build();
        final Image cocoyamimage1 = Image.builder().created(LocalDate.now()).description("Coco yam big imag").gallery(cocoYamGallery).name("Coco yam big image").path("/images/gallery/yam/coco-yam/coco-yam-large.jpg").build();
        final Image cocoyamimage2 = Image.builder().created(LocalDate.now()).description("Coco yam open display").gallery(cocoYamGallery).name("Coco open display").path("/images/gallery/yam/coco-yam/coco-yam-open.jpg").build();
        final Image whiteyamimage = Image.builder().created(LocalDate.now()).description("White yam thumbnail").gallery(whiteYamGallery).name("White yam thumbnail").path("/images/gallery/yam/white-yam/white-yam-thumbnail.jpg").build();
        final Image whiteyamimage1 = Image.builder().created(LocalDate.now()).description("White yam big image").gallery(whiteYamGallery).name("White yam big image").path("/images/gallery/yam/white-yam/white-yam-open1.jpg").build();
        final Image whiteyamimage2 = Image.builder().created(LocalDate.now()).description("White yam open display").gallery(whiteYamGallery).name("White yam open display").path("/images/gallery/yam/white-yam/white-yam-open.jpg").build();
        final Image ewurayamimage = Image.builder().created(LocalDate.now()).description("Ewura yam thumbnail").gallery(ewuraYamGallery).name("Ewura yam thumbnail").path("/images/gallery/yam/ewura-yam/ewura-yam-large.jpg").build();
        final Image ewurayamimage1 = Image.builder().created(LocalDate.now()).description("Ewura yam big image").gallery(ewuraYamGallery).name("Ewura yam big image").path("/images/gallery/yam/ewura-yam/ewura-yam-large.jpg").build();
        final Image ewurayamimage2 = Image.builder().created(LocalDate.now()).description("Ewura yam open display").gallery(ewuraYamGallery).name("Ewura yam open display").path("/images/gallery/yam/ewura-yam/ewura-yam-open1.jpg").build();
        final Image cassavaYamOpen = Image.builder().created(LocalDate.now()).description("Open cassava yam big image").gallery(cassavaYamGallery).name("Open cassavabig image").path("/images/gallery/yam/cassava-yam/open-cassava-yam.jpg").build();
        final Image cassavaYamPackaged = Image.builder().created(LocalDate.now()).description("Packaged cassava yam").gallery(cassavaYamGallery).name("Packaged cassava yam").path("/images/gallery/yam/cassava-yam/packaged-cassava-yam.jpg").build();
        Stream.of(cocoyamimage,cocoyamimage1,cocoyamimage2,whiteyamimage,whiteyamimage1,whiteyamimage2, ewurayamimage,ewurayamimage1,ewurayamimage2,cassavaYamOpen,cassavaYamPackaged).forEach(imageRepository::save);

        //Garri images
        final Image openGariWhiteImage = Image.builder().created(LocalDate.now()).description("Open gari white").gallery(gariWhiteGallery).name("Open gari white").path("/images/gallery/gari/white/open-gari-white.jpg").build();
        final Image packagedGariWhiteImage = Image.builder().created(LocalDate.now()).description("Packaged gari white").gallery(gariWhiteGallery).name("Packaged gari white").path("/images/gallery/gari/white/packaged-gari-white.jpg").build();
        final Image openGariIjebuImage = Image.builder().created(LocalDate.now()).description("Open gari ijebu").gallery(gariIjebuGallery).name("Open gari ijebu").path("/images/gallery/gari/ijebu/open-gari-ijebu.jpg").build();
        final Image packagedGariIjebuImage = Image.builder().created(LocalDate.now()).description("Packaged gari ijebu").gallery(gariIjebuGallery).name("Packaged gari ijebu").path("/images/gallery/gari/ijebu/packaged-gari-ijebu.jpg").build();
        final Image openGariYellowImage = Image.builder().created(LocalDate.now()).description("Open gari yellow").gallery(gariYellowGallery).name("Open gari yellow").path("/images/gallery/gari/yellow/open-yellow-garri.jpeg").build();
        final Image packagedGariYellowImage = Image.builder().created(LocalDate.now()).description("Packaged gari yellow").gallery(gariYellowGallery).name("Packaged gari yellow").path("/images/gallery/gari/yellow/packaged-yellow-garri.jpeg").build();
        Stream.of(openGariWhiteImage,packagedGariWhiteImage,openGariIjebuImage,packagedGariIjebuImage,openGariYellowImage,packagedGariYellowImage).forEach(imageRepository::save);

        //Yam flour images
        final Image yamflouropenimage = Image.builder().created(LocalDate.now()).description("Open yam flour").gallery(flourYamGallery).name("Open yam flour").path("/images/gallery/flour/flour-yam/package-yam-flour.jpg").build();
        final Image yamflourpackageimage = Image.builder().created(LocalDate.now()).description("Package yam flour").gallery(flourYamGallery).name("Package yam flour").path("/images/gallery/flour/flour-yam/open-yam-flour.jpg").build();
        //Cassava flour images
        final Image cassavaflouropenimage = Image.builder().created(LocalDate.now()).description("Open cassava flour").gallery(flourCassavaGallery).name("Open cassava flour").path("/images/gallery/flour/flour-cassava/open-cassava-flour.jpg").build();
        final Image cassavaflourpackageimage = Image.builder().created(LocalDate.now()).description("Package cassava flour").gallery(flourCassavaGallery).name("Package cassava flour").path("/images/gallery/flour/flour-cassava/package-flour-cassava.png").build();
        //Plantain flour images
        final Image plantainflouropenimage = Image.builder().created(LocalDate.now()).description("Open plantain flour").gallery(flourPlantainGallery).name("Open plantain flour").path("/images/gallery/flour/flour-plantain/open-plantain-flour.jpeg").build();
        final Image plantainflourpackageimage = Image.builder().created(LocalDate.now()).description("Package plantain flour").gallery(flourPlantainGallery).name("Package plantain flour").path("/images/gallery/flour/flour-plantain/package-plantain-flour.jpeg").build();
        Stream.of(yamflouropenimage,yamflourpackageimage,cassavaflouropenimage,cassavaflourpackageimage,plantainflouropenimage,plantainflourpackageimage).forEach(imageRepository::save);

        //spices locust beans images
        final Image locustBeansOpenImage = Image.builder().created(LocalDate.now()).description("Open locust beans flour").gallery(locustBeansGallery).name("Open locust beans flour").path("/images/gallery/spices/iru/open-iru.jpg").build();
        final Image locustBeansPackagedImage = Image.builder().created(LocalDate.now()).description("Package locust beans flour").gallery(locustBeansGallery).name("Package locust beans flour").path("/images/gallery/spices/iru/packaged-iru.jpg").build();
        //spices curry images
        final Image curryOpenImage = Image.builder().created(LocalDate.now()).description("Open curry flour").gallery(curryGallery).name("Open curry flour").path("/images/gallery/spices/curry/open-curry.jpg").build();
        final Image curryPackagedImage = Image.builder().created(LocalDate.now()).description("Package curry flour").gallery(curryGallery).name("Package curry flour").path("/images/gallery/spices/curry/packaged-curry.jpg").build();
        //spices pepper images
        final Image pepperOpenImage = Image.builder().created(LocalDate.now()).description("Open pepper flour").gallery(pepperGallery).name("Open pepper flour").path("/images/gallery/spices/pepper/open-pepper.png").build();
        final Image pepperPackagedImage = Image.builder().created(LocalDate.now()).description("Package pepper flour").gallery(pepperGallery).name("Package pepper flour").path("/images/gallery/spices/pepper/packaged-pepper.png").build();
        Stream.of(locustBeansOpenImage,locustBeansPackagedImage,curryOpenImage,curryPackagedImage,pepperOpenImage,pepperPackagedImage).forEach(imageRepository::save);


        //cat fish images
        final Image catfishOpenImage = Image.builder().created(LocalDate.now()).description("Open cat fish").gallery(catFishGallery).name("Open cat fish").path("/images/gallery/dry-fish/catfish/open-catfish.jpg").build();
        final Image catfishPackagedImage = Image.builder().created(LocalDate.now()).description("Packaged cat fish").gallery(catFishGallery).name("Packaged cat fish").path("/images/gallery/dry-fish/catfish/packaged-catfish.jpeg").build();
        //cray fish images
        final Image crayfishOpenImage = Image.builder().created(LocalDate.now()).description("Open cray fish").gallery(crayfishGallery).name("Open cray fish").path("/images/gallery/dry-fish/crayfish/open-crayfish.png").build();
        final Image crayfishPackagedImage = Image.builder().created(LocalDate.now()).description("Packaged cray fish").gallery(crayfishGallery).name("Packaged cray fish").path("/images/gallery/dry-fish/crayfish/packaged-crayfish.jpg").build();
        //herring images
        final Image herringOpenImage = Image.builder().created(LocalDate.now()).description("Open herring fish").gallery(herringGallery).name("Open herring fish").path("/images/gallery/dry-fish/herring/open-shawa-fish-herring.jpg").build();
        final Image herringScaleOpenImage = Image.builder().created(LocalDate.now()).description("Open herring scale fish").gallery(herringGallery).name("Open herring scale fish").path("/images/gallery/dry-fish/herring/open-shawa-fish-herring1.jpg.png").build();
        final Image herringPackagedImage = Image.builder().created(LocalDate.now()).description("Packaged cray fish").gallery(herringGallery).name("Packaged cray fish").path("/images/gallery/dry-fish/herring/packaged-shawa-fish-herring.jpg").build();
        //oyster images
        final Image oysterOpenImage = Image.builder().created(LocalDate.now()).description("Open dry oyster").gallery(oysterGallery).name("Open dry oyster").path("/images/gallery/dry-fish/oyster/open-oyster.jpg").build();
        imageRepository.save(oysterOpenImage);
        Stream.of(catfishOpenImage,catfishPackagedImage,crayfishOpenImage,crayfishPackagedImage,herringOpenImage,herringPackagedImage,herringScaleOpenImage,oysterOpenImage).forEach(imageRepository::save);

        //Category  Gari, Flour-yam, Flour-cassava, Flour-plantain
        Category yams = Category.builder().name("Yams").gallery(cocoYamGallery).build();
        Category gari = Category.builder().name("Gari").gallery(gariWhiteGallery).build();
        Category flour = Category.builder().name("Flour").gallery(flourCassavaGallery).build();
        Category dryVegetable = Category.builder().name("Dry vegetable").gallery(okaziLeaveGallery).build();
        Category spices = Category.builder().name("Spices").gallery(pepperGallery).build();
        Category dryFish = Category.builder().name("Dry fish").gallery(catFishGallery).build();
        Stream.of(gari, flour, yams, dryVegetable, spices, dryFish).forEach(categoryRepository::save);

        // Flour product groups
        ProductGroup flourYam = ProductGroup.builder().name("Yam Flour product group").category(flour).gallery(flourYamGallery).build();
        ProductGroup flourCassava = ProductGroup.builder().name("Cassava Flour product group").category(flour).gallery(flourCassavaGallery).build();
        ProductGroup flourPlantain = ProductGroup.builder().name("Plantain Flour product group").category(flour).gallery(flourPlantainGallery).build();
        Stream.of(flourYam,flourCassava,flourPlantain).forEach(productGroupRepository::save);

        // Dry fish product groups
        ProductGroup catFish = ProductGroup.builder().name("Cat fish product group").category(dryFish).gallery(catFishGallery).build();
        ProductGroup crayFish = ProductGroup.builder().name("Crayfish Flour product group").category(dryFish).gallery(crayfishGallery).build();
        ProductGroup herringFish = ProductGroup.builder().name("Herring product group").category(dryFish).gallery(herringGallery).build();
        ProductGroup oyster = ProductGroup.builder().name("Oyster product group").category(dryFish).gallery(oysterGallery).build();
        Stream.of(catFish, crayFish, herringFish, oyster).forEach(productGroupRepository::save);

        // Dry vegetable product groups
        ProductGroup bitterLeave = ProductGroup.builder().name("Bitter leave product group").category(dryVegetable).gallery(bitterLeaveGallery).build();
        ProductGroup egusiGrounded = ProductGroup.builder().name("Grounded egusi product group").category(dryVegetable).gallery(egusiGroundedGallery).build();
        ProductGroup egusiSeed = ProductGroup.builder().name("Egusi seed product group").category(dryVegetable).gallery(egusiSeedGallery).build();
        ProductGroup ishapa = ProductGroup.builder().name("Ishapa product group").category(dryVegetable).gallery(ishapaGallery).build();
        ProductGroup mushrooms = ProductGroup.builder().name("Mushrooms product group").category(dryVegetable).gallery(mushroomsGallery).build();
        ProductGroup ogbonoGrounded = ProductGroup.builder().name("Grounded oghono product group").category(dryVegetable).gallery(ogbonoGroundedGallery).build();
        ProductGroup ogbonoSeed = ProductGroup.builder().name("Oghono seed product group").category(dryVegetable).gallery(ogbonoSeedGallery).build();
        ProductGroup okaziLeave = ProductGroup.builder().name("Okazi leave product group").category(dryVegetable).gallery(okaziLeaveGallery).build();
        Stream.of(bitterLeave, egusiGrounded, egusiSeed, ishapa, mushrooms, ogbonoGrounded, ogbonoSeed, okaziLeave).forEach(productGroupRepository::save);

        //Garri product groups
        ProductGroup yellowGarri = ProductGroup.builder().name("Garri yellow product group").category(gari).gallery(gariYellowGallery).build();
        ProductGroup ijebuGarri = ProductGroup.builder().name("Garri ijebu product group").category(gari).gallery(gariIjebuGallery).build();
        ProductGroup whiteGarri = ProductGroup.builder().name("Garri white product group").category(gari).gallery(gariWhiteGallery).build();
        Stream.of(yellowGarri,whiteGarri,ijebuGarri).forEach(productGroupRepository::save);

        //Spices product groups
        ProductGroup curry = ProductGroup.builder().name("Curry product group").category(spices).gallery(curryGallery).build();
        ProductGroup locustBeans = ProductGroup.builder().name("Locust beans product group").category(spices).gallery(locustBeansGallery).build();
        ProductGroup pepper = ProductGroup.builder().name("Pepper product group").category(spices).gallery(pepperGallery).build();
        Stream.of(curry,locustBeans,pepper).forEach(productGroupRepository::save);

        //Yams product group
        ProductGroup cocoYam = ProductGroup.builder().name("Coco yam product group").category(yams).gallery(cocoYamGallery).build();
        ProductGroup whiteYam = ProductGroup.builder().name("White yam product group").category(yams).gallery(whiteYamGallery).build();
        ProductGroup ewuraYam = ProductGroup.builder().name("Water yam product group").category(yams).gallery(ewuraYamGallery).build();
        ProductGroup cassavaYam = ProductGroup.builder().name("Cassava yam product group").category(yams).gallery(cassavaYamGallery).build();
        Stream.of(cocoYam,whiteYam,ewuraYam,cassavaYam).forEach(productGroupRepository::save);//--------------------------------------

        //product code yams
        final ProductCode productCodeCocoYamFOC = ProductCode.builder().code("FOCCOCOYAM01").description("FOC Coco yam product code").brand(foc).productGroup(cocoYam).build();
        final ProductCode productCodeWhiteYamMedvacc = ProductCode.builder().code("MEDVACCWHITEYAM01").description("Medvacc White yam product code").brand(medvacc).productGroup(whiteYam).build();
        final ProductCode productCodeCassavaYamAyoola = ProductCode.builder().code("AYOOLACASASAVYAM01").description("Ayoola Cassava yam product code").brand(ayoola).productGroup(cassavaYam).build();
        final ProductCode productCodeCocoYamMedvacc = ProductCode.builder().code("MEDVACCCocoYAM02").description("Medvacc Coco yam product code").brand(medvacc).productGroup(cocoYam).build();
        final ProductCode productCodeWhiteYamAyoola = ProductCode.builder().code("AYOOLAWHITEYAM02").description("Ayoola White yam product code").brand(ayoola).productGroup(whiteYam).build();
        final ProductCode productCodeEwuraYamAyoola = ProductCode.builder().code("AYOOLAEWURAYAM01").description("Ayoola ewura yam product code").brand(ayoola).productGroup(ewuraYam).build();
        final ProductCode productCodeEwuraYamAfrimash = ProductCode.builder().code("AFRIMASHEWURAYAM01").description("AFRIMASH ewura yam product code").brand(afrimash).productGroup(ewuraYam).build();
        final ProductCode productCodeCassavaYamAfrimash = ProductCode.builder().code("AFRIMASHCASASAVYAM01").description("AFRIMASH Cassava yam product code").brand(afrimash).productGroup(cassavaYam).build();
        Stream.of(productCodeCocoYamFOC,productCodeWhiteYamMedvacc,productCodeCassavaYamAyoola,productCodeCocoYamMedvacc,productCodeWhiteYamAyoola,
                productCodeEwuraYamAyoola,productCodeEwuraYamAfrimash,productCodeCassavaYamAfrimash).forEach(productCodeRepository::save);

        // product code gari
        final ProductCode productCodeAyoolaGariIjebu = ProductCode.builder().code("AYOOLAGARIIJUBU01").description("Ayoola Gari ijebu product code").brand(ayoola).productGroup(ijebuGarri).build();
        final ProductCode productCodeAyoolaGariWhite = ProductCode.builder().code("AYOOLAGARIWHITE01").description("Ayoola Gari white product code").brand(ayoola).productGroup(whiteGarri).build();
        final ProductCode productCodeAyoolaGariYellow = ProductCode.builder().code("AYOOLAGARIYELLOW01").description("Ayoola Gari yellow product code").brand(ayoola).productGroup(yellowGarri).build();
        final ProductCode productCodeFOCGariWhite = ProductCode.builder().code("FOCGARIWHITE02").description("FOC gari white product code").brand(foc).productGroup(whiteGarri).build();
        final ProductCode productCodeFOCGariYellow = ProductCode.builder().code("FOCGARIYELLOW02").description("FOC gari yellow product code").brand(foc).productGroup(yellowGarri).build();
        final ProductCode productCodeFOCGariIjebu = ProductCode.builder().code("FOCGARIIJUBU02").description("FOC Gari ijebu product code").brand(foc).productGroup(ijebuGarri).build();

        final ProductCode productCodeAfrimashGariIjebu = ProductCode.builder().code("AFRIMASHGARIIJUBU03").description("Afrimash Gari ijebu product code").brand(afrimash).productGroup(ijebuGarri).build();
        final ProductCode productCodeAfrimashGariWhite = ProductCode.builder().code("AFRIMASHGARIWHITE03").description("Afrimash Gari white product code").brand(afrimash).productGroup(whiteGarri).build();
        final ProductCode productCodeAfrimashGariYellow = ProductCode.builder().code("AFRIMASHGARIYELLOW03").description("Afrimash Gari yellow product code").brand(afrimash).productGroup(yellowGarri).build();
        final ProductCode productCodeMedvaccGariWhite = ProductCode.builder().code("MEDVACCGARIWHITE04").description("Medvacc gari white product code").brand(medvacc).productGroup(whiteGarri).build();
        final ProductCode productCodeMedvaccGariYellow = ProductCode.builder().code("MEDVACCGARIYELLOW04").description("Medvacc gari yellow product code").brand(medvacc).productGroup(yellowGarri).build();
        final ProductCode productCodeMedvaccGariIjebu = ProductCode.builder().code("MEDVACCGARIIJUBU04").description("Medvacc Gari ijebu product code").brand(medvacc).productGroup(ijebuGarri).build();

        Stream.of(productCodeAyoolaGariIjebu,productCodeAyoolaGariWhite,productCodeAyoolaGariYellow,productCodeFOCGariWhite,productCodeFOCGariYellow,productCodeFOCGariIjebu,
                productCodeAfrimashGariIjebu,productCodeAfrimashGariWhite,productCodeAfrimashGariYellow,productCodeMedvaccGariWhite,productCodeMedvaccGariYellow,productCodeMedvaccGariIjebu).forEach(productCodeRepository::save);

        //product code flours
        final ProductCode medvaccYamFlourCode = ProductCode.builder().code("MEDVACCYAMFLOUR01").description("Medvacc yam flour product code").brand(medvacc).productGroup(flourYam).build();
        final ProductCode medvaccPlantainFlourCode = ProductCode.builder().code("MEDVACCPLANTAINFLOUR01").description("Medvacc plantain flour product code").brand(medvacc).productGroup(flourPlantain).build();
        final ProductCode medvaccCassavaFlourCode = ProductCode.builder().code("MEDVACCCASSAVAFLOUR01").description("Medvacc cassava flour product code").brand(medvacc).productGroup(flourCassava).build();
        final ProductCode ayoolaCassavaFlourCode = ProductCode.builder().code("AYOOLACASSAVAFLOUR01").description("Ayoola cassava flour product code").brand(ayoola).productGroup(flourCassava).build();
        final ProductCode ayoolaPlantainFlourCode = ProductCode.builder().code("AYOOLAPLANTAINFLOUR01").description("Ayoola plantain flour product code").brand(ayoola).productGroup(flourPlantain).build();
        final ProductCode ayoolaYamFlourCode = ProductCode.builder().code("AYOOLAYAMFLOUR01").description("Ayoola yam flour product code").brand(ayoola).productGroup(flourYam).build();
        final ProductCode afrimashPlantainFlourCode = ProductCode.builder().code("AFRIMASHPLANTAINFLOUR01").description("Afrimash plantain flour product code").brand(afrimash).productGroup(flourPlantain).build();
        final ProductCode afrimashYamFlourCode = ProductCode.builder().code("AFRIMASHYAMFLOUR01").description("Afrimash yam flour product code").brand(afrimash).productGroup(flourYam).build();
        final ProductCode afrimashCassavaFlourCode = ProductCode.builder().code("AFRIMASCASSAVAFLOUR01").description("Afrimash cassava flour product code").brand(afrimash).productGroup(flourCassava).build();
        final ProductCode focCassavaFlourCode = ProductCode.builder().code("FOCCASSAVAFLOUR01").description("FOC cassava flour product code").brand(foc).productGroup(flourCassava).build();
        final ProductCode focPlantainFlourCode = ProductCode.builder().code("FOCPLANTAINFLOUR01").description("FOC plantain flour product code").brand(foc).productGroup(flourPlantain).build();
        final ProductCode focYamFlourCode = ProductCode.builder().code("FOCPLANTAINFLOUR01").description("FOC yam flour product code").brand(foc).productGroup(flourYam).build();

        Stream.of(medvaccYamFlourCode,ayoolaCassavaFlourCode,ayoolaPlantainFlourCode,medvaccPlantainFlourCode,focCassavaFlourCode,focPlantainFlourCode,afrimashPlantainFlourCode,
                medvaccCassavaFlourCode,ayoolaYamFlourCode,afrimashYamFlourCode,afrimashCassavaFlourCode,focYamFlourCode).forEach(productCodeRepository::save);

        //product code dry vegetable
        final ProductCode medvaccBitterLeaveCode = ProductCode.builder().code("MEDVACCBTTERLEAVE01").description("Medvacc bitter leave product code").brand(medvacc).productGroup(bitterLeave).build();
        final ProductCode afrimashBitterLeaveCode = ProductCode.builder().code("AFRIMASHBITTERLEAVE01").description("AFRIMASH bitter leave product code").brand(afrimash).productGroup(bitterLeave).build();
        final ProductCode ayoolaEgusiGroundedCode = ProductCode.builder().code("AYOOLAEGUSIGROUNDED01").description("Ayoola egusi grounded product code").brand(ayoola).productGroup(egusiGrounded).build();
        final ProductCode afrimashEgusiGroundedCode = ProductCode.builder().code("AFRIMASEGUSIGROUNDED01").description("AFRIMASH egusi grounded product code").brand(afrimash).productGroup(egusiGrounded).build();
        final ProductCode ayoolaEgusiSeedCode = ProductCode.builder().code("AYOOLAEGUSISEED01").description("Ayoola egusi seed product code").brand(ayoola).productGroup(egusiSeed).build();
        final ProductCode focEgusiSeedCode = ProductCode.builder().code("FOCEGUSISEED01").description("FOC egusi seed product code").brand(foc).productGroup(egusiSeed).build();
        final ProductCode medvaccIshapaCode = ProductCode.builder().code("MEDVACCISHAPA01").description("Medvacc ishapa product code").brand(medvacc).productGroup(ishapa).build();
        final ProductCode focIshapaCode = ProductCode.builder().code("FOCISHAPA01").description("FOC ishapa product code").brand(foc).productGroup(ishapa).build();
        final ProductCode ayoolaMushroomsCode = ProductCode.builder().code("AYOOLAMUSHROOMS01").description("Ayoola muchrooms product code").brand(ayoola).productGroup(mushrooms).build();
        final ProductCode ayoolaOgbonoGroundedCode = ProductCode.builder().code("AYOOLAOGBONOGROUNDED01").description("Ayoola ogbono grounded product code").brand(ayoola).productGroup(ogbonoGrounded).build();
        final ProductCode medvaccOgbonoSeedCode = ProductCode.builder().code("MEDVACCOGBONOSEED01").description("Medvacc ogbono seed product code").brand(medvacc).productGroup(ogbonoSeed).build();
        final ProductCode ayoolaOkaziLeaveCode = ProductCode.builder().code("AYOOLAOKAZILEAVE01").description("Ayoola okazi leave product code").brand(ayoola).productGroup(okaziLeave).build();
        final ProductCode focOgbonoGroundedCode = ProductCode.builder().code("FOCOGBONOGROUNDED02").description("FOC ogbono grounded product code").brand(foc).productGroup(ogbonoGrounded).build();
        final ProductCode afrimashOgbonoGroundedCode = ProductCode.builder().code("AFRIMASHOGBONOGROUNDED02").description("AFRIMASH ogbono grounded product code").brand(afrimash).productGroup(ogbonoGrounded).build();
        final ProductCode ayoolaOgbonoSeedCode = ProductCode.builder().code("AYOOLAOGBONOSEED02").description("Ayoola ogbono seed product code").brand(ayoola).productGroup(ogbonoSeed).build();
        final ProductCode medvaccOkaziLeaveCode = ProductCode.builder().code("MEDVACCOKAZILEAVE02").description("Medvacc okazi leave product code").brand(medvacc).productGroup(okaziLeave).build();
        Stream.of(medvaccBitterLeaveCode,ayoolaEgusiGroundedCode,ayoolaEgusiSeedCode,medvaccIshapaCode,ayoolaMushroomsCode,ayoolaOgbonoGroundedCode,medvaccOgbonoSeedCode,afrimashBitterLeaveCode,ayoolaOkaziLeaveCode,
                focOgbonoGroundedCode,ayoolaOgbonoSeedCode,medvaccOkaziLeaveCode,focEgusiSeedCode,focIshapaCode,afrimashOgbonoGroundedCode,afrimashEgusiGroundedCode).forEach(productCodeRepository::save);

        //spices product code
        final ProductCode medvaccCurryCode = ProductCode.builder().code("MEDVACCCURRY01").description("Medvacc curry product code").brand(medvacc).productGroup(curry).build();
        final ProductCode ayoolaPepperCode = ProductCode.builder().code("AYOOLAPEPPER01").description("Ayoola pepper product code").brand(ayoola).productGroup(pepper).build();
        final ProductCode ayoolaLocustBeansCode = ProductCode.builder().code("AYOOLALOCUSTBEANS01").description("Ayoola locust beans product code").brand(ayoola).productGroup(locustBeans).build();
        final ProductCode focCurryCode = ProductCode.builder().code("FOCCCURRY02").description("FOC curry product code").brand(foc).productGroup(curry).build();
        final ProductCode medvaccPepperCode = ProductCode.builder().code("MEDVACPEPPER02").description("Medvacc pepper product code").brand(medvacc).productGroup(pepper).build();
        final ProductCode medvaccLocustBeansCode = ProductCode.builder().code("MEDVACLOCUSTBEANS02").description("Medvacc locust beans product code").brand(medvacc).productGroup(locustBeans).build();
        Stream.of(medvaccCurryCode,ayoolaPepperCode,ayoolaLocustBeansCode,focCurryCode,medvaccPepperCode,medvaccLocustBeansCode).forEach(productCodeRepository::save);

        //Dry fish product code
        final ProductCode medvaccCatFishCode = ProductCode.builder().code("MEDVACCCATFISH01").description("Medvacc cat fish product code").brand(medvacc).productGroup(catFish).build();
        final ProductCode medvaccCrayFishCode = ProductCode.builder().code("MEDVACCCRAYFISH01").description("Medvacc cray fish product code").brand(medvacc).productGroup(crayFish).build();
        final ProductCode medvaccHerringFishCode = ProductCode.builder().code("MEDVACCHERRINGFISH01").description("Medvacc herring fish product code").brand(medvacc).productGroup(herringFish).build();
        final ProductCode medvaccOysterCode = ProductCode.builder().code("MEDVACCOYSTER01").description("Medvacc oyster product code").brand(medvacc).productGroup(oyster).build();
        final ProductCode ayoolaCatFishCode = ProductCode.builder().code("AYOOLACATFISH02").description("Ayoola cat fish product code").brand(ayoola).productGroup(catFish).build();
        final ProductCode ayoolaCrayFishCode = ProductCode.builder().code("AYOOLACRAYFISH02").description("Ayoola cray fish product code").brand(ayoola).productGroup(crayFish).build();
        final ProductCode ayoolaHerringFishCode = ProductCode.builder().code("AYOOLAHERRINGFISH02").description("Ayoola herring fish product code").brand(ayoola).productGroup(herringFish).build();
        final ProductCode ayoolaOysterCode = ProductCode.builder().code("AYOOLAOYSTER02").description("Ayoola oyster product code").brand(ayoola).productGroup(oyster).build();
        final ProductCode focCatFishCode = ProductCode.builder().code("FOCCATFISH03").description("FOC cat fish product code").brand(foc).productGroup(catFish).build();
        final ProductCode focCrayFishCode = ProductCode.builder().code("FOCCRAYFISH03").description("FOC cray fish product code").brand(foc).productGroup(crayFish).build();
        final ProductCode focHerringFishCode = ProductCode.builder().code("FOCHERRINGFISH03").description("FOC herring fish product code").brand(foc).productGroup(herringFish).build();
        final ProductCode focOysterCode = ProductCode.builder().code("FOCOYSTER03").description("FOC oyster product code").brand(foc).productGroup(oyster).build();
        final ProductCode afrimashCatFishCode = ProductCode.builder().code("AFRIMASHCATFISH04").description("Afrimash cat fish product code").brand(afrimash).productGroup(catFish).build();
        final ProductCode afrimashCrayFishCode = ProductCode.builder().code("AFRIMASHCRAYFISH04").description("Afrimash cray fish product code").brand(afrimash).productGroup(crayFish).build();
        final ProductCode afrimashHerringFishCode = ProductCode.builder().code("AFRIMASHHERRINGFISH04").description("Afrimash herring fish product code").brand(afrimash).productGroup(herringFish).build();
        final ProductCode afrimashOysterCode = ProductCode.builder().code("AFRIMASHOYSTER04").description("Afrimash oyster product code").brand(afrimash).productGroup(oyster).build();
        Stream.of(medvaccCatFishCode,medvaccCrayFishCode,medvaccHerringFishCode,medvaccOysterCode,ayoolaCatFishCode,ayoolaCrayFishCode,ayoolaHerringFishCode,ayoolaOysterCode,focCatFishCode,focCrayFishCode,focHerringFishCode,
                focOysterCode,afrimashCatFishCode,afrimashCrayFishCode,afrimashHerringFishCode,afrimashOysterCode).forEach(productCodeRepository::save);

        //Product weights Yam
        ProductWeight productWeightCocoaYam = ProductWeight.builder().description("Cocoa Yam weight").build();
        ProductWeight productWeightWhiteYam = ProductWeight.builder().description("White Yam weight").build();
        ProductWeight productWeightEwuraYam = ProductWeight.builder().description("Ewura Yam weight").build();
        ProductWeight productWeightCassavaYam = ProductWeight.builder().description("Cassava Yam weight").build();

        //Product weights Gari
        ProductWeight productWeightGariWhite = ProductWeight.builder().description("White Gari  weight").build();
        ProductWeight productWeightGariYellow = ProductWeight.builder().description("Yellow Gari weight").build();
        ProductWeight productWeightGariIjebu = ProductWeight.builder().description("Ijebu Gari weight").build();

        //Product weights Flour
        ProductWeight productWeightFlourCassava = ProductWeight.builder().description("Cassava Flour  weight").build();
        ProductWeight productWeightFlourPlantain = ProductWeight.builder().description("Plantain Flour weight").build();
        ProductWeight productWeightFlourYam = ProductWeight.builder().description("Yam Flour weight").build();

        //Product weights Dry vegetable
        ProductWeight productWeightBitterLeaveDryVegetable = ProductWeight.builder().description("Bitter Leave Dry Vegetable weight").build();
        ProductWeight productWeightEgusiGroundedDryVegetable = ProductWeight.builder().description("Egusi Grounded Dry Vegetable weight").build();
        ProductWeight productWeightEgusiSeedDryVegetable = ProductWeight.builder().description("Egusi Seed Dry Vegetable weight").build();
        ProductWeight productWeightIsapaDryVegetable = ProductWeight.builder().description("Isapa Dry Vegetable weight").build();
        ProductWeight productWeightMushroomsDryVegetable = ProductWeight.builder().description("Mushrooms Dry Vegetable weight").build();
        ProductWeight productWeightOgbonoGroundedDryVegetable = ProductWeight.builder().description("Ogbono Grounded Dry Vegetable weight").build();
        ProductWeight productWeightOgbonoSeedDryVegetable = ProductWeight.builder().description("Ogbono Seed Dry Vegetable weight").build();
        ProductWeight productWeightOkaziLeaveDryVegetable = ProductWeight.builder().description("Okazi Leave Dry Vegetable weight").build();

//        ProductWeight productGramWeight250 = ProductWeight.builder().productWeightBase(ProductWeightBase.g).value(250).build();
//        ProductWeight productGramWeight500 = ProductWeight.builder().productWeightBase(ProductWeightBase.g).value(500).build();
//        ProductWeight productGramWeight750 = ProductWeight.builder().productWeightBase(ProductWeightBase.g).value(750).build();
//        ProductWeight productKGWeight1 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(1).build();
//        ProductWeight productKGWeight2 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(2).build();
//        ProductWeight productKGWeight3 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(3).build();
//        ProductWeight productKGWeight4 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(4).build();
//        ProductWeight productKGWeight5 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(5).build();
//        ProductWeight productKGWeight10 = ProductWeight.builder().productWeightBase(ProductWeightBase.kg).value(10).build();
        Stream.of(productWeightCocoaYam,productWeightWhiteYam,productWeightEwuraYam,productWeightCassavaYam,productWeightBitterLeaveDryVegetable,productWeightGariWhite,productWeightGariYellow,
                productWeightGariIjebu,productWeightFlourCassava,productWeightFlourPlantain,productWeightFlourYam,productWeightEgusiGroundedDryVegetable,productWeightEgusiSeedDryVegetable,
                productWeightIsapaDryVegetable,productWeightMushroomsDryVegetable,productWeightOgbonoGroundedDryVegetable,productWeightOgbonoSeedDryVegetable,productWeightOkaziLeaveDryVegetable).forEach(productWeightRepository::save);

        //product weightList Yam
        WeightList ewuraYamWeightListKG1 = WeightList.builder().productWeight(productWeightEwuraYam).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList ewuraYamWeightListKG2 = WeightList.builder().productWeight(productWeightEwuraYam).productWeightBase(ProductWeightBase.g).value(2000).price(price1).build();
        WeightList ewuraYamWeightListKG5 = WeightList.builder().productWeight(productWeightEwuraYam).productWeightBase(ProductWeightBase.g).value(5000).price(price2).build();

        WeightList whiteYamWeightListKG1 = WeightList.builder().productWeight(productWeightWhiteYam).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList whiteYamWeightListKG3 = WeightList.builder().productWeight(productWeightWhiteYam).productWeightBase(ProductWeightBase.g).value(3000).price(price1).build();
        WeightList whiteYamWeightListKG5 = WeightList.builder().productWeight(productWeightWhiteYam).productWeightBase(ProductWeightBase.g).value(5000).price(price2).build();

        WeightList cocoaYamWeightListKG1 = WeightList.builder().productWeight(productWeightCocoaYam).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList cocoaYamWeightListKG5 = WeightList.builder().productWeight(productWeightCocoaYam).productWeightBase(ProductWeightBase.g).value(5000).price(price3).build();

        WeightList cassavaYamWeightListKG1 = WeightList.builder().productWeight(productWeightCassavaYam).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList cassavaYamWeightListKG2 = WeightList.builder().productWeight(productWeightCassavaYam).productWeightBase(ProductWeightBase.g).value(2000).price(price1).build();
        WeightList cassavaYamWeightListKG5 = WeightList.builder().productWeight(productWeightCassavaYam).productWeightBase(ProductWeightBase.g).value(5000).price(price2).build();

        //product weightList Gari
        WeightList ijebuGariWeightListKG1 = WeightList.builder().productWeight(productWeightGariIjebu).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList ijebuGariWeightListKG2 = WeightList.builder().productWeight(productWeightGariIjebu).productWeightBase(ProductWeightBase.g).value(2000).price(price2).build();
        WeightList ijebuGariWeightListKG5 = WeightList.builder().productWeight(productWeightGariIjebu).productWeightBase(ProductWeightBase.g).value(5000).price(price3).build();

        WeightList yellowGariWeightListKG1 = WeightList.builder().productWeight(productWeightGariYellow).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList yellowGariWeightListKG2 = WeightList.builder().productWeight(productWeightGariYellow).productWeightBase(ProductWeightBase.g).value(2000).price(price1).build();
        WeightList yellowGariWeightListKG5 = WeightList.builder().productWeight(productWeightGariYellow).productWeightBase(ProductWeightBase.g).value(5000).price(price2).build();

        WeightList whiteGariWeightListKG1 = WeightList.builder().productWeight(productWeightGariWhite).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList whiteGariWeightListKG5 = WeightList.builder().productWeight(productWeightGariWhite).productWeightBase(ProductWeightBase.g).value(5000).price(price2).build();

        //product weightList Flour
        WeightList yamFlourWeightListKG1 = WeightList.builder().productWeight(productWeightFlourYam).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList yamFlourWeightListKG2 = WeightList.builder().productWeight(productWeightFlourYam).productWeightBase(ProductWeightBase.g).value(2000).price(price2).build();
        WeightList yamFlourWeightListKG5 = WeightList.builder().productWeight(productWeightFlourYam).productWeightBase(ProductWeightBase.g).value(5000).price(price4).build();

        WeightList plantainFlourWeightListKG1 = WeightList.builder().productWeight(productWeightFlourPlantain).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList plantainFlourWeightListKG5 = WeightList.builder().productWeight(productWeightFlourPlantain).productWeightBase(ProductWeightBase.g).value(5000).price(price3).build();

        WeightList cassavaFlourWeightListKG1 = WeightList.builder().productWeight(productWeightFlourCassava).productWeightBase(ProductWeightBase.g).value(1000).price(price).build();
        WeightList cassavaFlourWeightListKG2 = WeightList.builder().productWeight(productWeightFlourCassava).productWeightBase(ProductWeightBase.g).value(2000).price(price2).build();
        WeightList cassavaFlourWeightListKG5 = WeightList.builder().productWeight(productWeightFlourCassava).productWeightBase(ProductWeightBase.g).value(5000).price(price3).build();

        //product weightList Dry Leave
        WeightList bitterLeaveDryVegetableWeightListG250 = WeightList.builder().productWeight(productWeightBitterLeaveDryVegetable).productWeightBase(ProductWeightBase.g).value(250).price(price).build();
        WeightList bitterLeaveDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightBitterLeaveDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price1).build();

        WeightList egusiGroundedDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightEgusiGroundedDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price2).build();
        WeightList egusiGroundedDryVegetableWeightListG750 = WeightList.builder().productWeight(productWeightEgusiGroundedDryVegetable).productWeightBase(ProductWeightBase.g).value(750).price(price3).build();

        WeightList egusiSeedDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightEgusiSeedDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price).build();
        WeightList egusiSeedDryVegetableWeightListKG1 = WeightList.builder().productWeight(productWeightEgusiSeedDryVegetable).productWeightBase(ProductWeightBase.g).value(1000).price(price3).build();

        WeightList isapaDryVegetableWeightListG250 = WeightList.builder().productWeight(productWeightIsapaDryVegetable).productWeightBase(ProductWeightBase.g).value(250).price(price).build();
        WeightList isapaDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightIsapaDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price3).build();

        WeightList mushroomsDryVegetableWeightListG250 = WeightList.builder().productWeight(productWeightMushroomsDryVegetable).productWeightBase(ProductWeightBase.g).value(250).price(price).build();
        WeightList mushroomsDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightMushroomsDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price1).build();

        WeightList ogbonoGroundedDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightOgbonoGroundedDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price2).build();
        WeightList ogbonoGroundedDryVegetableWeightListG750 = WeightList.builder().productWeight(productWeightOgbonoGroundedDryVegetable).productWeightBase(ProductWeightBase.g).value(750).price(price3).build();

        WeightList ogbonoSeedDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightOgbonoSeedDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price).build();
        WeightList ogbonoSeedDryVegetableWeightListKG1 = WeightList.builder().productWeight(productWeightOgbonoSeedDryVegetable).productWeightBase(ProductWeightBase.g).value(1000).price(price3).build();

        WeightList okaziLeaveDryVegetableWeightListG250 = WeightList.builder().productWeight(productWeightOkaziLeaveDryVegetable).productWeightBase(ProductWeightBase.g).value(250).price(price).build();
        WeightList okaziLeaveDryVegetableWeightListG500 = WeightList.builder().productWeight(productWeightOkaziLeaveDryVegetable).productWeightBase(ProductWeightBase.g).value(500).price(price3).build();
        Stream.of(ewuraYamWeightListKG1,ewuraYamWeightListKG2,ewuraYamWeightListKG5,whiteYamWeightListKG1,whiteYamWeightListKG3,whiteYamWeightListKG5,cocoaYamWeightListKG1,cocoaYamWeightListKG5,
                cassavaYamWeightListKG1,cassavaYamWeightListKG2,cassavaYamWeightListKG5,ijebuGariWeightListKG1,ijebuGariWeightListKG2,ijebuGariWeightListKG5,yellowGariWeightListKG1,
                yellowGariWeightListKG2,yellowGariWeightListKG5,whiteGariWeightListKG1,whiteGariWeightListKG5,yamFlourWeightListKG1,yamFlourWeightListKG2,yamFlourWeightListKG5,
                plantainFlourWeightListKG1,plantainFlourWeightListKG5,cassavaFlourWeightListKG1,cassavaFlourWeightListKG2,cassavaFlourWeightListKG5,bitterLeaveDryVegetableWeightListG250,
                bitterLeaveDryVegetableWeightListG500,egusiGroundedDryVegetableWeightListG500,egusiGroundedDryVegetableWeightListG750,egusiSeedDryVegetableWeightListG500,
                egusiSeedDryVegetableWeightListKG1,isapaDryVegetableWeightListG250,isapaDryVegetableWeightListG500,mushroomsDryVegetableWeightListG250,mushroomsDryVegetableWeightListG500,
                ogbonoGroundedDryVegetableWeightListG500,ogbonoGroundedDryVegetableWeightListG750,ogbonoSeedDryVegetableWeightListG500,ogbonoSeedDryVegetableWeightListKG1,
                okaziLeaveDryVegetableWeightListG250,okaziLeaveDryVegetableWeightListG500).forEach(weightListRepository::save);


        //Product yams
        final Product cocoaYamFOCProduct = Product.builder().name("Cocoyam FOC product").description("Small Cocoyam FOC product").created(LocalDate.now().minusDays(8)).productGroup(cocoYam).gallery(cocoYamGallery)
                .country(null).productCode(productCodeCocoYamFOC).productWeight(productWeightCocoaYam).build();

        final Product whiteYamMedvaccProduct = Product.builder().name("White yam medvacc product").description("White yam medvacc product").created(LocalDate.now()).productGroup(whiteYam).gallery(whiteYamGallery)
               .country(null).productCode(productCodeWhiteYamMedvacc).productWeight(productWeightWhiteYam).build();
        final Product coocYamMedvaccProduct = Product.builder().name("Cocoyam medvacc product").description("Small Cocoyam medvacc product").created(LocalDate.now()).productGroup(cocoYam).gallery(cocoYamGallery)
                .country(null).productCode(productCodeCocoYamMedvacc).productWeight(productWeightCocoaYam).build();

        final Product ewuraYamAyoolaProduct = Product.builder().name("Ewura yam ayoola product").description("Ewura yam ayoola product").created(LocalDate.now()).productGroup(ewuraYam).gallery(ewuraYamGallery)
                .country(null).productCode(productCodeEwuraYamAyoola).productWeight(productWeightEwuraYam).build();
        final Product whiteYamAyoolaProduct = Product.builder().name("White yam ayoola product").description("White yam ayoola product").created(LocalDate.now().minusDays(8)).productGroup(whiteYam).gallery(whiteYamGallery)
                .country(null).productCode(productCodeWhiteYamAyoola).productWeight(productWeightWhiteYam).build();
        final Product cassavaYamAyoolaProduct = Product.builder().name("Cassava yam ayoola product").description("Cassava yam ayoola product").created(LocalDate.now().minusDays(8)).productGroup(cassavaYam).gallery(cassavaYamGallery)
                .country(null).productCode(productCodeCassavaYamAyoola).productWeight(productWeightCassavaYam).build();

        final Product ewuraYamAfimashProduct = Product.builder().name("Ewura yam afrimash product").description("Ewura yam afrimash product").created(LocalDate.now()).productGroup(ewuraYam).gallery(ewuraYamGallery)
                .country(null).productCode(productCodeEwuraYamAfrimash).productWeight(productWeightEwuraYam).build();
        final Product cassavaYamAfrimashProduct = Product.builder().name("Cassava yam afrimash product").description("Cassava yam afrimash product").created(LocalDate.now().minusDays(8)).productGroup(cassavaYam).gallery(cassavaYamGallery)
                .country(null).productCode(productCodeCassavaYamAfrimash).productWeight(productWeightCassavaYam).build();
//        final Product ewuraYamAfimashProductKG5 = Product.builder().name("Ewura yam afrimash product").description("Ewura yam afrimash product").created(LocalDate.now()).price(price2).productGroup(ewuraYam).gallery(ewuraYamGallery)
//                .country(null).productCode(productCodeEwuraYamAfrimash).productWeight(productKGWeight5).build();

//        final Product cassavaYamAfrimashProductKG10 = Product.builder().name("Cassava yam afrimash product").description("Cassava yam afrimash product").created(LocalDate.now().minusDays(8)).price(price2).productGroup(cassavaYam).gallery(cassavaYamGallery)
//                .country(null).productCode(productCodeCassavaYamAfrimash).productWeight(productKGWeight10).build();
        Stream.of(cocoaYamFOCProduct,whiteYamMedvaccProduct,ewuraYamAyoolaProduct,coocYamMedvaccProduct,whiteYamAyoolaProduct,cassavaYamAyoolaProduct,ewuraYamAfimashProduct,
             cassavaYamAfrimashProduct).forEach(productRepository::save);

        //Product gari
        final Product ijebuGarriAyoolaProduct = Product.builder().name("Ayoola gari ijebu product").description("Ayoola gari ijebu product").created(LocalDate.now()).productCode(productCodeAyoolaGariIjebu)
                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productWeightGariIjebu).build();
        final Product whiteGarriAyoolaProduct = Product.builder().name("Ayoola gari white product").description("Ayoola gari white product").created(LocalDate.now().minusDays(8)).productCode(productCodeAyoolaGariWhite)
                .productGroup(whiteGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariWhite).build();
        final Product yellowGarriAyoolaProduct = Product.builder().name("Ayoola gari yellow product").description("Ayoola gari yellow product").created(LocalDate.now().minusDays(8)).productCode(productCodeAyoolaGariYellow)
                .productGroup(yellowGarri).gallery(gariYellowGallery).country(null).productWeight(productWeightGariYellow).build();
//        final Product ijebuGarriAyoolaProductKG2 = Product.builder().name("Ayoola gari ijebu product").description("Ayoola gari ijebu product").created(LocalDate.now().minusDays(8)).price(price3).productCode(productCodeAyoolaGariIjebu)
//                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productKGWeight2).build();
//        final Product whiteGarriAyoolaProductKG2 = Product.builder().name("Ayoola gari white product").description("Ayoola gari white product").created(LocalDate.now().minusDays(8)).price(price2).productCode(productCodeAyoolaGariWhite)
//                .productGroup(whiteGarri).gallery(gariWhiteGallery).country(null).productWeight(productKGWeight2).build();


        final Product ijebuGarriFOCProduct = Product.builder().name("FOC gari ijebu product").description("FOC gari ijebu product").created(LocalDate.now()).productCode(productCodeFOCGariIjebu)
                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productWeightGariIjebu).build();
        final Product whiteGarriFOCProduct = Product.builder().name("FOC gari white product").description("FOC gari white product").created(LocalDate.now().minusDays(8)).productCode(productCodeFOCGariWhite)
                .productGroup(whiteGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariWhite).build();
        final Product yellowGarriFOCProduct = Product.builder().name("FOC gari yellow product").description("FOC gari yellow product").created(LocalDate.now().minusDays(8)).productCode(productCodeFOCGariYellow)
                .productGroup(yellowGarri).gallery(gariYellowGallery).country(null).productWeight(productWeightGariYellow).build();
//        final Product ijebuGarriFOCProductKG2 = Product.builder().name("FOC gari ijebu product").description("FOC gari ijebu product").created(LocalDate.now().minusDays(8)).price(price3).productCode(productCodeFOCGariIjebu)
//                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productKGWeight2).build();


        final Product ijebuGarriAfrimashProduct = Product.builder().name("Afrimash gari ijebu product").description("Afrimash gari ijebu product").created(LocalDate.now()).productCode(productCodeAfrimashGariIjebu)
                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productWeightGariIjebu).build();
        final Product whiteGarriAfrimashProduct = Product.builder().name("Afrimash gari white product").description("Afrimash gari white product").created(LocalDate.now().minusDays(8)).productCode(productCodeAfrimashGariWhite)
                .productGroup(whiteGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariWhite).build();
        final Product yellowGarriAfrimashProduct = Product.builder().name("Afrimash gari yellow product").description("Afrimash gari yellow product").created(LocalDate.now().minusDays(8)).productCode(productCodeAfrimashGariYellow)
                .productGroup(yellowGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariYellow).build();
//        final Product ijebuGarriAfrimashProductKG2 = Product.builder().name("Afrimash gari ijebu product").description("Afrimash gari ijebu product").created(LocalDate.now().minusDays(8)).price(price3).productCode(productCodeAfrimashGariIjebu)
//                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productKGWeight2).build();


        final Product ijebuGarriMedvaccProduct = Product.builder().name("Medvacc gari ijebu product").description("Medvacc gari ijebu product").created(LocalDate.now()).productCode(productCodeMedvaccGariIjebu)
                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productWeightGariIjebu).build();
        final Product whiteGarriMedvaccProduct = Product.builder().name("Medvacc gari white product").description("Medvacc gari white product").created(LocalDate.now().minusDays(8)).productCode(productCodeMedvaccGariWhite)
                .productGroup(whiteGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariWhite).build();
        final Product yellowGarriMedvaccProduct = Product.builder().name("Medvacc gari yellow product").description("Medvacc gari yellow product").created(LocalDate.now().minusDays(8)).productCode(productCodeMedvaccGariYellow)
                .productGroup(yellowGarri).gallery(gariWhiteGallery).country(null).productWeight(productWeightGariYellow).build();
//        final Product ijebuGarriMedvaccProductKG2 = Product.builder().name("Medvacc gari ijebu product").description("Medvacc gari ijebu product").created(LocalDate.now().minusDays(8)).price(price3).productCode(productCodeMedvaccGariIjebu)
//                .productGroup(ijebuGarri).gallery(gariIjebuGallery).country(null).productWeight(productKGWeight2).build();

        Stream.of(ijebuGarriAyoolaProduct,whiteGarriAyoolaProduct,yellowGarriAyoolaProduct,ijebuGarriFOCProduct,whiteGarriFOCProduct,
                yellowGarriFOCProduct,ijebuGarriAfrimashProduct,whiteGarriAfrimashProduct,yellowGarriAfrimashProduct,yellowGarriMedvaccProduct,whiteGarriMedvaccProduct,ijebuGarriMedvaccProduct).forEach(productRepository::save);


          //Product flours
        final Product flourYamMedvaccProduct = Product.builder().name("Medvacc flour yam product").description("Medvacc flour yam product").created(LocalDate.now())
                .productCode(medvaccYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productWeightFlourYam).build();
        final Product flourCassavaMedvaccProduct = Product.builder().name("Medvacc flour cassava product").description("Medvacc flour cassava product").created(LocalDate.now())
                .productCode(medvaccCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productWeightFlourCassava).build();
        final Product flourPlantainMedvaccProduct = Product.builder().name("Medvacc flour plantain product").description("Medvacc flour plantain product").created(LocalDate.now())
                .productCode(medvaccPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productWeightFlourPlantain).build();
//        final Product flourYamMedvaccProductKG5 = Product.builder().name("Medvacc flour yam product").description("Medvacc flour yam product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(medvaccYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourCassavaMedvaccProductKG5 = Product.builder().name("Medvacc flour cassava product").description("Medvacc flour cassava product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(medvaccCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourPlantainMedvaccProductKG5 = Product.builder().name("Medvacc flour plantain product").description("Medvacc flour plantain product").created(LocalDate.now()).price(price4)
//                .productCode(medvaccPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productKGWeight5).build();

        final Product flourYamAyoolaProduct = Product.builder().name("Ayoola flour yam product").description("Ayoola flour yam product").created(LocalDate.now())
                .productCode(ayoolaYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productWeightFlourYam).build();
        final Product flourCassavaAyoolaProduct = Product.builder().name("Ayoola flour cassava product").description("Ayoola flour cassava product").created(LocalDate.now())
                .productCode(ayoolaCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productWeightFlourCassava).build();
        final Product flourPlantainAyoolaProduct = Product.builder().name("Ayoola flour plantain product").description("Ayoola flour plantain product").created(LocalDate.now())
                .productCode(ayoolaPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productWeightFlourPlantain).build();
//        final Product flourYamAyoolaProductKG5 = Product.builder().name("Ayoola flour yam product").description("Ayoola flour yam product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(ayoolaYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourCassavaAyoolaProductKG5 = Product.builder().name("Ayoola flour cassava product").description("Ayoola flour cassava product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(ayoolaCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourPlantainAyoolaProductKG5 = Product.builder().name("Ayoola flour plantain product").description("Ayoola flour plantain product").created(LocalDate.now()).price(price4)
//                .productCode(ayoolaPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productKGWeight5).build();

        final Product flourYamFOCProduct = Product.builder().name("FOC flour yam product").description("FOC flour yam product").created(LocalDate.now())
                .productCode(focYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productWeightFlourYam).build();
        final Product flourCassavaFOCProduct = Product.builder().name("FOC flour cassava product").description("FOC flour cassava product").created(LocalDate.now())
                .productCode(focCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productWeightFlourCassava).build();
        final Product flourPlantainFOCProduct = Product.builder().name("FOC flour plantain product").description("FOC flour plantain product").created(LocalDate.now())
                .productCode(focPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productWeightFlourPlantain).build();
//        final Product flourCassavaFOCProductKG5 = Product.builder().name("FOC flour cassava product").description("FOC flour cassava product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(focCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourYamFOCProductKG5 = Product.builder().name("FOC flour yam product").description("FOC flour yam product").created(LocalDate.now().minusDays(8)).price(price4)
//                .productCode(focYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourPlantainFOCProductKG5 = Product.builder().name("FOC flour plantain product").description("FOC flour plantain product").created(LocalDate.now()).price(price4)
//                .productCode(focPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productKGWeight5).build();

        final Product flourYamAfrimashProduct = Product.builder().name("Afrimash flour yam product").description("Afrimash flour yam product").created(LocalDate.now())
                .productCode(afrimashYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productWeightFlourYam).build();
        final Product flourCassavaAfrimashProduct = Product.builder().name("Afrimash flour cassava product").description("Afrimash flour cassava product").created(LocalDate.now())
                .productCode(afrimashCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productWeightFlourCassava).build();
        final Product flourPlantainAfrimashProduct = Product.builder().name("Afrimash flour plantain product").description("Afrimash flour plantain product").created(LocalDate.now())
                .productCode(afrimashPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productWeightFlourPlantain).build();
//        final Product flourYamAfrimashProductKG5 = Product.builder().name("Afrimash flour yam product").description("Afrimash flour yam product").created(LocalDate.now().minusDays(8)).price(price3)
//                .productCode(afrimashYamFlourCode).productGroup(flourYam).gallery(flourYamGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourCassavaAfrimashProductKG5 = Product.builder().name("Afrimash flour cassava product").description("Afrimash flour cassava product").created(LocalDate.now().minusDays(8)).price(price3)
//                .productCode(afrimashCassavaFlourCode).productGroup(flourCassava).gallery(flourCassavaGallery).country(null).productWeight(productKGWeight5).build();
//        final Product flourPlantainAfrimashProductKG5 = Product.builder().name("Afrimash flour plantain product").description("Afrimash flour plantain product").created(LocalDate.now()).price(price3)
//                .productCode(afrimashPlantainFlourCode).productGroup(flourPlantain).gallery(flourPlantainGallery).country(null).productWeight(productKGWeight5).build();


        Stream.of(flourYamMedvaccProduct,flourCassavaMedvaccProduct,flourPlantainMedvaccProduct,flourYamAyoolaProduct,
                flourCassavaAyoolaProduct,flourPlantainAyoolaProduct,flourYamFOCProduct,flourCassavaFOCProduct, flourPlantainFOCProduct,flourYamAfrimashProduct,flourCassavaAfrimashProduct,flourPlantainAfrimashProduct).forEach(productRepository::save);

        //Product dry vegetable
        final Product bitterLeaveAfrimashProduct = Product.builder().name("Afrimash  Bitter leave product").description("Afrimash  Bitter leave product").created(LocalDate.now()).
                productCode(afrimashBitterLeaveCode).productGroup(bitterLeave).gallery(bitterLeaveGallery).country(null).productWeight(productWeightBitterLeaveDryVegetable).build();
        final Product egusiGroundedAfrimashProduct = Product.builder().name("Afrimash  Egusi grounded product product").description("Afrimash  Egusi grounded product").created(LocalDate.now()).
                productCode(afrimashEgusiGroundedCode).productGroup(egusiGrounded).gallery(egusiGroundedGallery).country(null).productWeight(productWeightEgusiGroundedDryVegetable).build();

        final Product isapaFOCProduct = Product.builder().name("FOC Isapa product").description("FOC Isapa product").created(LocalDate.now()).
                productCode(focIshapaCode).productGroup(ishapa).gallery(ishapaGallery).country(null).productWeight(productWeightIsapaDryVegetable).build();
        final Product egusiSeedFOCProduct = Product.builder().name("FOC Egusi Seed product product").description("FOC Egusi Seed product product").created(LocalDate.now()).
                productCode(focEgusiSeedCode).productGroup(egusiSeed).gallery(egusiSeedGallery).country(null).productWeight(productWeightEgusiSeedDryVegetable).build();

        final Product mushroomsAyoolaProduct = Product.builder().name("Ayoola  Mushrooms product").description("Ayoola  Mushrooms product").created(LocalDate.now()).
                productCode(ayoolaMushroomsCode).productGroup(mushrooms).gallery(mushroomsGallery).country(null).productWeight(productWeightMushroomsDryVegetable).build();
        final Product ogbonoGroundedAyoolaProduct = Product.builder().name("Ayoola  Ogbono grounded product").description("Ayoola  Ogbono grounded product").created(LocalDate.now()).
                productCode(ayoolaOgbonoGroundedCode).productGroup(ogbonoGrounded).gallery(ogbonoGroundedGallery).country(null).productWeight(productWeightOgbonoGroundedDryVegetable).build();

        final Product okaziLeaveMedvaccProduct = Product.builder().name("Medvacc Okazi leave product").description("Medvacc Okazi leave product").created(LocalDate.now()).
                productCode(medvaccOkaziLeaveCode).productGroup(okaziLeave).gallery(okaziLeaveGallery).country(null).productWeight(productWeightOkaziLeaveDryVegetable).build();
        final Product ogbonoSeedMedvaccProduct = Product.builder().name("Medvacc Ogbono seed product").description("Medvacc Ogbono seed product").created(LocalDate.now()).
                productCode(medvaccOgbonoSeedCode).productGroup(ogbonoSeed).gallery(ogbonoSeedGallery).country(null).productWeight(productWeightOgbonoSeedDryVegetable).build();

        Stream.of(bitterLeaveAfrimashProduct,egusiGroundedAfrimashProduct,isapaFOCProduct,egusiSeedFOCProduct, mushroomsAyoolaProduct,ogbonoGroundedAyoolaProduct,okaziLeaveMedvaccProduct,ogbonoSeedMedvaccProduct).forEach(productRepository::save);
//
//        final Product egusiGroundedProduct = Product.builder().name("Egusi grounded product").description("Egusi grounded product").created(LocalDate.now().minusDays(8)).price(price1).brand(ayoola).category(dryVegetable).gallery(egusiGroundedGallery).country(null).productCode(ayoolaEgusiGroundedCode).build();
//        final Product egusiSeedProduct = Product.builder().name("Egusi seed product").description("Egusi seed product").created(LocalDate.now().minusDays(8)).price(price1).brand(ayoola).category(dryVegetable).gallery(egusiSeedGallery).country(null).productCode(ayoolaEgusiSeedCode).build();
//        final Product ishapaProduct = Product.builder().name("Ishapa product").description("Ishapa product").created(LocalDate.now()).price(price).brand(medvacc).category(dryVegetable).gallery(ishapaGallery).country(null).productCode(medvaccIshapaCode).build();
//        final Product mushroomsProduct = Product.builder().name("Mushrooms product").description("Mushrooms product").created(LocalDate.now().minusDays(8)).price(price1).brand(ayoola).category(dryVegetable).gallery(mushroomsGallery).country(null).productCode(ayoolaMushroomsCode).build();
//        final Product ogbonoGroundedProduct = Product.builder().name("Ogbono grounded product").description("Ogbono grounded  product").created(LocalDate.now().minusDays(8)).price(price1).brand(ayoola).category(dryVegetable).gallery(ogbonoGroundedGallery).country(null).productCode(ayoolaOgbonoGroundedCode).build();
//        final Product ogbonoSeedProduct = Product.builder().name("Ogbono seed product").description("Ogbono seed product").created(LocalDate.now()).price(price).brand(medvacc).category(dryVegetable).gallery(ogbonoSeedGallery).country(null).productCode(medvaccOgbonoSeedCode).build();
//        final Product okaziLeaveProduct = Product.builder().name("Okazi leave product").description("Okazi leave product").created(LocalDate.now().minusDays(8)).price(price1).brand(ayoola).category(dryVegetable).gallery(okaziLeaveGallery).country(null).productCode(ayoolaOkaziLeaveCode).build();
////
//        //Product spices
//        final Product curryProduct = Product.builder().name("Curry product").description("Curry product").created(LocalDate.now()).price(price).brand(medvacc).category(spices).gallery(curryGallery).country(null).productCode(medvaccCurryCode).build();
//        this.productRepository.save(curryProduct);
//        final Product locustBeansProduct = Product.builder().name("Locust beans product").description("Locust bean product").created(LocalDate.now()).price(price1).brand(ayoola).category(spices).gallery(locustBeansGallery).country(null).productCode(ayoolaLocustBeansCode).build();
//        this.productRepository.save(locustBeansProduct);
//        final Product pepperProduct = Product.builder().name("Pepper product").description("Pepper product").created(LocalDate.now().minusDays(8)).price(price2).brand(ayoola).category(spices).gallery(pepperGallery).country(null).productCode(ayoolaPepperCode).build();
//        this.productRepository.save(pepperProduct);
    }
}
