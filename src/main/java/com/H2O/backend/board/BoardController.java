package com.H2O.backend.board;

import com.H2O.backend.util.boardEnum.Messenger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3306)
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


    public BoardController(BoardRepository boardRepository, BoardService boardService){
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }

    //글쓰기 업로드
    @PostMapping("/update")
    public ResponseEntity<List<Board>> getAllBoardList(@RequestBody Board board){
        Board Bdata = new Board();
        Bdata.setTitle(board.getTitle());
        Bdata.setContent(board.getContent());
        Bdata.setCreationDate(board.getCreationDate());
        Bdata.setMedCategory(board.getMedCategory());
        Bdata.setCustomerCategory(board.getCustomerCategory());
        Bdata.setQuestionCategory(board.getQuestionCategory());
        Bdata.setCategory(board.getCategory());
        Bdata.setClick(board.getClick());
        Bdata.setCreationDate(LocalDate.now());

        boardRepository.save(Bdata);
        List<Board> boardList = boardService.findAll();
        return ResponseEntity.ok(boardList);
    }
    //카테고리별 리스트 빼오기
    @GetMapping("/list/get/{category}")
    public ResponseEntity<List<Board>> getBoardList(@PathVariable String category) {
        System.out.println(category);
        List<Board> boardList = boardService.findByCategory(category);
        System.out.println(boardList.toString());
        return ResponseEntity.ok(boardList);
    }

    //삭제
    @DeleteMapping("/list/delete/{boardNo}")
    public Messenger getDeleteBoard(@PathVariable String boardNo){
        Optional<Board> result = boardService.findBoardNo(Long.parseLong(boardNo));
        Board deleteResult = result.get();
        boardService.delete(deleteResult);
        return Messenger.SUCCEESS;
    }

    //수정
    @PatchMapping("/modify/{boardNo}")
    public Messenger getModifyBoard(@RequestBody Board board,
                                    @PathVariable String boardNo){
        Optional<Board> getOne = boardService.findBoardNo(Long.parseLong(boardNo));
        Board result =  getOne.get();
        try{
            result.setContent(board.getContent());
            result.setTitle(board.getTitle());
            boardRepository.save(result);
            return Messenger.SUCCEESS;
        }catch (Exception e){
            e.printStackTrace();
            return Messenger.FAIL;
        }
    }

    //리스트에서 진료카테고리별
    @GetMapping("/list/{medCategory}")
    public List<Board> getMedCateBoard(@PathVariable String medCategory){
        System.out.println(medCategory);
        List<Board> findOne = boardService.findOneBoard(medCategory);
        System.out.println(findOne);
        return findOne;
    }

    //클릭
    @GetMapping("/list/getOne/{boardNo}")
    public Optional<Board> getOneBoardNo(@PathVariable String boardNo){
        System.out.println(boardService.findBoardNo(Long.parseLong(boardNo)));
        boardService.click(Long.parseLong(boardNo));
        return boardService.findBoardNo(Long.parseLong(boardNo));
    }
}