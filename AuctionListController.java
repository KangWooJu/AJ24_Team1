package ac.kr.dankook.ace.dom_t1.controller;


import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionBidEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRegisterEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.SiteuserEntity;
import ac.kr.dankook.ace.dom_t1.Model.Service.AuctionBidService;
import ac.kr.dankook.ace.dom_t1.Model.Service.AuctionRegisterService;
import ac.kr.dankook.ace.dom_t1.Model.Service.SiteuserService;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/domAuction")
@RequiredArgsConstructor
@Controller
public class AuctionListController {

    private final AuctionRegisterService auctionRegisterService;
    private final AuctionBidService auctionBidService;
    private final SiteuserService siteuserService;


    // ( 6.3 추가 )
    @GetMapping("/main")
    public String mainWeb_Category(Model model)
    {
       return "Dom_main";
    }

    @GetMapping("/list") // 옥션의 리스트를 보여주는 메소드 
    @ResponseBody
    public String list(Model model,@RequestParam(value="page", defaultValue="0") int page, @RequestParam( value="input", defaultValue="0") String input,@RequestParam(value="category", required=false) String category) {
        Page<AuctionRegisterEntity> paging = this.auctionRegisterService.getList(page,input); // 페이지,input(검색기능)을 받아온 후에 모델에 넘겨주기
        model.addAttribute("paging", paging); // 페이징 모델 Add
        model.addAttribute("input",input); // input 모델 Add -> 검색 기능 모델 
        return "AuctionList"; 
    }

    // ( 6 . 3 수정 : 컨트롤러 ) 리스트 페이지에서 카테고리 버튼을 클릭했을 때 얻을 수 있는 화면
    @GetMapping("/list/sortByCategory/{category_num}")
    public String sortByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "input", required = false) String input,
            @PathVariable(name = "category_num") int category_num, // ( 6.3 수정 -> PathVariable 추가 )
            Model model) {
        
        // 카테고리에 따라 경매 목록 정렬하여 가져오기
        Page<AuctionRegisterEntity> auctionPage = auctionRegisterService.getItems(category, page, input,category_num); // ( 6.3 카테고리 전용 url 생성하기 위해 추가 )
        model.addAttribute("paging", auctionPage);
        return "auctionList"; // ( 6.2 수정 : HTML 이름 대소문자 오류 해결 )
    }

    

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/detail/{username}") // 옥션의 리스트 중 하나를 클릭했을 때 얻을 수 있는 화면 : 상세 페이지 -> HTML 내부에 링크 추가 필요 ( 질문 목록에 링크 추가하기 ): https://wikidocs.net/161302
    public String detail(Model model, @PathVariable("username") String username,@Valid AuctionBidForm auctionBidForm ,AuctionRequestForm auctionRequestForm, AuctionBidEntity auctionBidEntity) {
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(username); // 등록Service의 get 메소드 사용 
        model.addAttribute("auctionRegisterEntity",auctionRegisterEntity); // 모델에 넣어주기 

        AuctionBidEntity highestBid = auctionBidService.findHighestBidder(auctionBidEntity);
        model.addAttribute("highestBid", highestBid); // 최고가격을 보여주는 model 삽입 ( 5.29 수정 )
        model.addAttribute("auctionBidForm",auctionBidForm); // ( 6.3 수정 ) -> bidForm 추가
        return "Auction_detail"; // Model : bidCounter -> 현재 입찰자 수 
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/bid")
    public String placeBid(@Valid AuctionBidForm auctionBidForm, BindingResult bindingResult, Principal principal, String username) {
        
        
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/detail/%s", auctionBidForm.getUsername());
        }

        SiteuserEntity bidder = siteuserService.getUser(principal.getName());
        auctionBidService.createAuctionBid(auctionBidForm.getUsername(), bidder, auctionBidForm.getBidAmount());
        return String.format("redirect:/DomAuction/detail/%s", auctionBidForm.getUsername());
    }

    // 글 작성 페이지 : 
    @PreAuthorize("isAuthenticated()") 
    @GetMapping("/DomAuction/create") // 글 작성 페이지 : Get 방식으로 화면 띄우기 
    public String auctionRegisterCreate(AuctionRegisterForm auctionRegisterForm,Model model){
        model.addAttribute("auctionRegisterForm",auctionRegisterForm);
        return "post-product";
    }

    // ( 6.3 수정 -> 파라미터에 category_num 추가 )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/DomAuction/create") // 글 작성 페이지 : 
    public String auctionRegisterCreate(@Valid AuctionRegisterForm auctionRegisterForm , BindingResult bindingResult,Principal principal){ 
        if (bindingResult.hasErrors()) { //valid에 맞지 않을 경우 리턴
            return "post-product"; // 검증 실패시에 대한 오류 처리 주석 필요 -> 
        }
        SiteuserEntity siteuserEntity = this.siteuserService.getUser(principal.getName());
        this.auctionRegisterService.AuctionRegisterCreate(auctionRegisterForm.getTitle(), auctionRegisterForm.getContent(),auctionRegisterForm.getLocationCode(),auctionRegisterForm.getUser_month(),auctionRegisterForm.getUser_day(),auctionRegisterForm.getCategory(),auctionRegisterForm.getCategory_num(),siteuserEntity,auctionRegisterForm.getPrice()); // AuctionRegisterService에 파라미터를 전달하고 메소드 시행 , principal객체를 통해서 사용자명을 얻은 후에 SiteuserEntity를 조회하여 옥션 등록글 저장시에 함께 저장
        return "redirect:/DomAuction/list";
    }


    // 게시글 삭제 모듈 주석 처리 부탁드립니다. ( 6.3 수정 )


    /* 
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{username}") //게시글 수정 모듈 : GetMapping ( 화면 )
    public String AuctionRegisterModify(AuctionRegisterForm auctionRegisterForm, @PathVariable("username") String username, Principal principal)
    {
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(username); // auctionRegisterService 객체 받아오기 -> username 받아오기
        if(!auctionRegisterEntity.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."); 
        }
        // 글쓴이 = 사용자가 같은 경우 성공 -> 
        auctionRegisterForm.setTitle(auctionRegisterEntity.getTitle()); // 글 제목 받아오기 
        auctionRegisterForm.setContent(auctionRegisterEntity.getContent()); // 글 내용 받아오기 
        auctionRegisterForm.setLocationCode(auctionRegisterEntity.getLocationCode()); // 지역 코드 받아오기 ( 5.24 수정 )
        auctionRegisterForm.setUser_month(auctionRegisterEntity.getUser_month()); // 경매 시작 월 받아오기 ( 5.24 수정 )
        auctionRegisterForm.setUser_day(auctionRegisterEntity.getUser_day()); // 경매 시작 일 받아오기 ( 5.24 수정 )
        auctionRegisterForm.setCategory(auctionRegisterEntity.getCategory()); // 경매 카테고리 받아오기 ( 5.24 수정 )
        return "post-product";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{username}") // 게시물 수정 모듈 : Post요청 처리 ( 전달 ) , AuctionRegisterForm 의 데이터를 검증 , 수정자와 작성자가 동일한지 검증
    public String AuctionRegisterModify(@Valid AuctionRegisterForm auctionRegisterForm , BindingResult bindingResult , Principal principal , @PathVariable("username") String username )
    {
        if (bindingResult.hasErrors()) { 
            return "post-product";
        }
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(username);
        if (!auctionRegisterEntity.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."); // 수정권한이 없으면 메시지 띄우기 
        } // 검증 성공시 ReigsterService의 modify 메소드 호출 -> 등록글 수정
        this.auctionRegisterService.modify(auctionRegisterEntity, auctionRegisterForm.getTitle(), auctionRegisterForm.getContent(),auctionRegisterForm.getLocationCode(),auctionRegisterForm.getUser_month(),auctionRegisterForm.getUser_day(),auctionRegisterForm.getCategory()); // 지역코드 , 경매 월-일 추가 ( 5.24 수정)
        return String.format("redirect:/question/detail/%s", username);
    }// 수정이 완료되면 redirect ( 등록 상세 페이지 )
    */

    // 글 삭제 하기 ( GET )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{username}")
    public String AuctionReigsterDelete(Principal principal, @PathVariable("uesrname") String username){
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(username); // 이름 받아오기 
        if(!auctionRegisterEntity.getAuthor().getUsername().equals(principal.getName())){ // -> 삭제자 와 글쓴이가 같은지 확인 
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제권한이 없습니다.");
        }
        this.auctionRegisterService.delete(auctionRegisterEntity); // 같다면 delete 실행 : HTML 파일 참고 필요 ( 버튼 추가하고 서비스와 컨트롤러 수정하기 ) https://wikidocs.net/162413
        return "redirect:/";
    }

    // 글 삭제 하기 ( POST )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/good/{username}")
    public String AuctionRegisterGood(Principal principal, @PathVariable("username") String username){
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(username); // 글 작성자 정보로부터 username을  받아오기
        SiteuserEntity siteuserEntity = this.siteuserService.getUser(principal.getName()); // 동록된 사용자로부터 name 받아오기 
        this.auctionRegisterService.good(auctionRegisterEntity, siteuserEntity); // good 메소드 사용 
        return String.format("redirect:/DomAuction/detail/%s",username);
    }
    // 비밀번호 찾기 페이지 구현 
}
