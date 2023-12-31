package org.zerock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDto;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.PageDto;
import org.zerock.service.Board.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

   private BoardService service;

   // 1. 리스트 페이지
   @GetMapping("/list")
   public void list(CriteriaDto cri, Model model) {
      log.info("list 처리가 이루어집니다!" + cri);
      model.addAttribute("list", service.getList(cri));

      int total = service.getTotal(cri);
      log.info("전체 데이터 수량= " + total);
      model.addAttribute("pageMaker", new PageDto(cri, total));
   }

   // 2. 새로운 항목을 등록
   @GetMapping("/register")

   // 3. 사용자가 인증
   @PreAuthorize("isAuthenticated()")
   public void register() {
   }

   // 4. 글 등록
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/register")
   public String register(BoardDto board, RedirectAttributes rttr) {
      log.info("글 등록: " + board);
      service.register(board);

      rttr.addFlashAttribute("result", board.getBno());
      return "redirect:/board/list";
   }

   // 5. GET 요청
   @GetMapping({ "/get", "/modify" })
   public void get(
         @RequestParam("bno") Long bno,
         @ModelAttribute("cri") CriteriaDto cri, Model model) {

      log.info("/get or modify");
      model.addAttribute("board", service.get(bno));
   }

   // 6. 게시판 글 정보를 수정
   @PreAuthorize("principal.username == #board.writer")
   @PostMapping("/modify")
   public String modify(
         BoardDto board, @ModelAttribute("cri") CriteriaDto cri,
         RedirectAttributes rttr) {

      log.info("글수정 처리가 되었습니다! ==> " + board);

      if (service.modify(board)) {
         rttr.addFlashAttribute("result", "success");
      }
      rttr.addAttribute("pageNum", cri.getPageNum());
      rttr.addAttribute("amount", cri.getAmount());
      rttr.addAttribute("type", cri.getType());
      rttr.addAttribute("keyword", cri.getKeyword());

      return "redirect:/board/list";
   }

   // 7. 게시판 글 삭제
   @PreAuthorize("principal.username == #writer")
   @PostMapping("/remove")
   public String remove(
         @RequestParam("bno") Long bno,
         @ModelAttribute("cri") CriteriaDto cri,
         RedirectAttributes rttr, String writer) {
      log.info("글삭제 처리가 되었습니다! ==> " + bno);

      if (service.remove(bno)) {
         rttr.addFlashAttribute("result", "success");
      }

      rttr.addAttribute("pageNum", cri.getPageNum());
      rttr.addAttribute("amount", cri.getAmount());

      return "redirect:/board/list";
   }

}