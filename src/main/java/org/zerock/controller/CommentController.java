package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.CommentDto;
import org.zerock.dto.CriteriaDto;
import org.zerock.dto.ReplyDto;
import org.zerock.service.Comment.CommentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class CommentController {

      private CommentService service;

      // 1. 댓글 등록
      @PreAuthorize("isAuthenticated()")
      @PostMapping(value = "/new",
            consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })

      public ResponseEntity<String> create(@RequestBody ReplyDto vo) {

            log.info("ReplyVO 댓글 객체 = " + vo);
            int insertCount = service.register(vo);
            log.info("등록된 댓글 개수 = " + insertCount);

            return insertCount == 1
                  ? new ResponseEntity<String>("success", HttpStatus.OK)
                  : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      // 2. 댓글 목록
      @GetMapping(value = "/pages/{bno}/{page}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_UTF8_VALUE })

      public ResponseEntity<CommentDto> getList(
                  @PathVariable("page") int page,
                  @PathVariable("bno") Long bno) {

            CriteriaDto cri = new CriteriaDto(page, 10);
                  log.info("특정 게시글에 등록된 댓글의 리스트 목록 내용 확인! ==> " + bno);
                  log.info(cri);

            return new ResponseEntity<CommentDto>(service.getListPage(cri, bno), HttpStatus.OK);
      }

      // 3. 댓글 상세조회
      @GetMapping(value = "/{rno}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_UTF8_VALUE })

      public ResponseEntity<ReplyDto> get(@PathVariable("rno") Long rno) {

            log.info("댓글 상세 조회 내용: " + rno);
            return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
      }

      // 4. 댓글 수정
      @PreAuthorize("principal.username == #vo.replyer")
      @RequestMapping(method = { RequestMethod.PUT,RequestMethod.PATCH },
            value = "/modify/{rno}",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE })
      public ResponseEntity<String> modify(@RequestBody ReplyDto vo, @PathVariable("rno") Long rno) {
            vo.setRno(rno);

            log.info("rno 댓글 번호 = " + rno);
            log.info("modify 수정 처리 = " + vo);

            return service.modify(vo) == 1
                  ? new ResponseEntity<String>("success", HttpStatus.OK)
                  : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
      // 5. 댓글 삭제
      @PreAuthorize("principal.username == #vo.replyer")
      @DeleteMapping(value = "/delete/{rno}",
            consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
      public ResponseEntity<String> remove(@RequestBody ReplyDto vo, @PathVariable("rno") Long rno) {

            log.info("댓글 삭제 처리: " + rno);
            log.info("replyer 댓글 작성자: " + vo.getReplyer());

            return service.remove(rno) == 1
                  ? new ResponseEntity<String>("success", HttpStatus.OK)
                  : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
}