package ac.kr.dankook.ace.dom_t1.controller;


import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRegisterEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRequestEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.SiteuserEntity;
import ac.kr.dankook.ace.dom_t1.Model.Service.AuctionRegisterService;
import ac.kr.dankook.ace.dom_t1.Model.Service.AuctionRequestService;
import ac.kr.dankook.ace.dom_t1.Model.Service.SiteuserService;

@RequestMapping("/request")
@RequiredArgsConstructor
@Controller
public class AuctionRequestController {

    private final AuctionRegisterService auctionRegisterService;
    private final AuctionRequestService auctionRequestService;
    private final SiteuserService siteuserService;

    // 댓글 작성 
    // ( 6.5 수정 -> username으로 받는 것을 id 로 변경 )
    @PreAuthorize("isAuthenticated()") // 로그인된상태 ( isAuthenticated = true 인 상태 ) 에서만 해당 url 사용가능 
    @PostMapping("/create/{id}") // URL 요청시에 createRequest 매서드가 호출되도록 @PostMapping 
    public String createRequest(Model model,@PathVariable("id") Integer id, @Valid AuctionRequestForm auctionRequestForm, BindingResult bindingResult, Principal principal) // content ( 댓글 내용 ) 를 requestparam으로 넘겨줌 , 시큐리티의 principal 객체 생성 
    { // ( 6.5 수정 : pathvariable 부분의 username을 id로 변경 해야함 )
        AuctionRegisterEntity auctionRegisterEntity = this.auctionRegisterService.getAuctionRegisterEntity(id); // username 받아오기 -> ( 6 .5 수정 사항 : username으로 받은 것을 모두 id)
        SiteuserEntity siteuserEntity = this.siteuserService.getUser(principal.getName()); // principal은 siteuserEntity객체의 getUsername() 메소드를 호출
        if (bindingResult.hasErrors()) { //valid 검증에 맞지 않을 경우 예외 처리를 통해 Auction_detial.html 을 출력하도록 한다. 
            model.addAttribute("auctionRegisterEntity", auctionRegisterEntity);
            return "Auction_detail"; // Auction_detail.html 파일안에 예외 처리 필요 , 참고 ( 템플릿 수정 ) : https://wikidocs.net/161911
        }
        AuctionRequestEntity auctionRequestEntity = this.auctionRequestService.createRequest(auctionRegisterEntity ,auctionRequestForm.getContent(),siteuserEntity);
        return String.format("redirect:/DomAuction/detail/%s#request_%s",auctionRequestEntity.getAuctionregisterentity().getId(),auctionRequestEntity.getId()); // ( 6. 5 수정 사항 : username을 id 로 변경 )
    }// 앵커기능 추가 -> 첫번째 %s : 글작성자의 이름을 가져옴 , 두번쨰 %s : 댓글 작성자의 이름을 가져옴 

    // 댓글 수정 ( Get )
    // ( 6. 5 수정 사항 -> username을 id 로 전부 변경 )
    @PreAuthorize("isAuthenticated()") 
    @GetMapping("/modify/{id}") // ( 6. 5 수정 사항 : username을 id 로 변경 )
    public String requestModify(AuctionRequestForm auctionRequestForm,@PathVariable("id") Integer id, Principal principal){
        AuctionRequestEntity auctionRequestEntity = this.auctionRequestService.getAuctionRequestEntity(id); //  ( 6. 5 수정 사항 -> username을 id 로 전부 변경 )
        if(!auctionRequestEntity.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다."); // 다르다면 수정권한 x
        }
        auctionRequestForm.setContent(auctionRequestEntity.getContent()); // 같다면 getContent를 통해 작성할 수 있도록함 
        return "Request_form";
    }

    // 댓글 수정 ( Post )
    // ( 6. 5 수정 사항 -> username을 id 로 전부 변경 )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String requestModify(@Valid AuctionRequestForm auctionRequestForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal){

        if(bindingResult.hasErrors()){ // Valid 양식에 맞지 않는경우 예외 처리 
            return "Request_form";
        }
        AuctionRequestEntity auctionRequestEntity = this.auctionRequestService.getAuctionRequestEntity(id); //  ( 6. 5 수정 사항 -> username을 id 로 전부 변경 )
        if(!auctionRequestEntity.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다."); // username과 작성자가 일치 하지 않으면 권한x
        }
        this.auctionRequestService.modify(auctionRequestEntity,auctionRequestForm.getContent()); // 일치하면 modify 모듈 실행 
        return String.format("redirect:DomAuction/detail/%s#request_%s",auctionRequestEntity.getAuctionregisterentity().getId(),auctionRequestEntity.getId()); // 수정 끝나면 redirect ( 6. 5 수정 사항 : username을 id 로 변경 )
    } // 앵커기능 추가 

    // 댓글 삭제 ( Get )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String requestDelete(Principal principal, @PathVariable("id") Integer id){
        AuctionRequestEntity auctionRequestEntity = this.auctionRequestService.getAuctionRequestEntity(id);
        if(!auctionRequestEntity.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다."); // username과 작성자가 일치 하지 않으면 권한x
        }
        this.auctionRequestService.delete(auctionRequestEntity); // 일치하면 delete 모듈 실행 
        return String.format("redirect/DomAuction/detial/%s",auctionRequestEntity.getAuctionregisterentity().getId()); // 수정 끝나면 redirect ( 6. 5 수정 사항 -> username을 id 로 전부 변경 )
    }

    /* 
    
    // 좋아요 기능 추가하기 
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/good/{username}")
    public String AuctionRequestGood(Principal principal,@PathVariable("username") String username){
        AuctionRequestEntity auctionRequestEntity = this.auctionRequestService.getAuctionRequestEntity(username); // 작성자 검색하기 
        SiteuserEntity siteuserEntity = this.siteuserService.getUser(principal.getName()); // 유저 받아오기
        this.auctionRequestService.good(auctionRequestEntity, siteuserEntity); // good 메소드 실행 
        return String.format("redirect:/DomAuction/detail/%s#request_%s",auctionRequestEntity.getAuctionregisterentity().getUsername(),auctionRequestEntity.getUsername()); // 실행 이후에 redirect
  
    }// 앵커 기능 추가 

    */


}
