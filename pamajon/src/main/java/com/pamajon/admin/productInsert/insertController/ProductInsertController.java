package com.pamajon.admin.productInsert.insertController;

import com.pamajon.admin.productInsert.insertModel.service.ProductInsertService;
import com.pamajon.admin.productInsert.insertModel.vo.ProductCategory;
import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import com.pamajon.admin.productInsert.insertModel.vo.WrapperCategory;
import com.pamajon.common.file.FileRename;
import com.pamajon.common.file.FileRenameStringType;
import com.pamajon.common.vo.CommonCodeDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/adminProduct")
@Log4j2
/**
 * @Author : Patrick Yoo
 */
public class ProductInsertController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductInsertController.class);

    @Qualifier("productInsertServiceImpl")
    private final ProductInsertService productInsertService;
    @Autowired
    FileRename fileRename;
    @Autowired
    FileRenameStringType fileRenameStringType;


    public ProductInsertController(ProductInsertService productInsertService){
       this.productInsertService=productInsertService;
    }

    @GetMapping("/brand")
    public ResponseEntity<List<ProductDto>> getProduct(){


        return new ResponseEntity<List<ProductDto>>(productInsertService.getBrand(), HttpStatus.OK);
    }
    @GetMapping("/wrapcategory")
    public ResponseEntity<List<WrapperCategory>> getWrapperCategory(){

        return new ResponseEntity<>(productInsertService.getWrapperCategory(),HttpStatus.OK);
    }

    @GetMapping("/category/{wrapperId}")
    public ResponseEntity<List<ProductCategory>> getCategory(@PathVariable(name = "wrapperId") int wrapperId){

        return new ResponseEntity<>(productInsertService.getCategory(wrapperId),HttpStatus.OK);
    }
    @GetMapping("/origin")
    public ResponseEntity<List<CommonCodeDto>> getOrigin()
    {

        return new ResponseEntity<>(productInsertService.getOrigin(),HttpStatus.OK);
    }
    @GetMapping("/sizeoption/{sizeOption}")
    public ResponseEntity<List<CommonCodeDto>> getSizeOption(@PathVariable(name="sizeOption") String sizeOption){


        return new ResponseEntity<>(productInsertService.getSizeOption(sizeOption),HttpStatus.OK);
    }

    @PostMapping("/product")
    public ModelAndView productInsert(ProductDto productDto,
                                      ProductOptionDto productOptionDto,
                                      @RequestParam(name="productImages") List<MultipartFile> productImages
                                     ,HttpServletRequest request
                                     ,ModelAndView mv) throws IOException {

      //product Insert.
        int productInsert = productInsertService.insertProduct(productDto);

        int optionInsert = productInsertService.optionInsert(productOptionDto.getOptionList());

        String filePath = System.getProperty("user.dir")+"/src/main/resources/static/images/productImages/";

        for (int i = 0 ; i<productImages.size(); i++){
            HashMap<String,String> fileMap = new HashMap<>();
            //
        if(i==0){
            String fileName = fileRenameStringType.fileRenameString(productImages.get(i).getOriginalFilename());

            fileMap.put("fileName",fileName);
            fileMap.put("fileStatus","1");
            //파일 저장.
            productImages.get(i).transferTo(new File(filePath+fileName));

            productInsertService.insertProductImages(fileMap);

            }
        if(i!=0){
            String fileName = fileRenameStringType.fileRenameString(productImages.get(i).getOriginalFilename());

            fileMap.put("fileName",fileName);
            fileMap.put("fileStatus","0");
            productImages.get(i).transferTo(new File(filePath+fileName));

            productInsertService.insertProductImages(fileMap);
        }

        }
        mv.setViewName("redirect:/admin/mainPage");

        return mv;
    }
}

