package com.example.my_first_spring_boot.service;

import com.example.my_first_spring_boot.entity.BoardEntity;
import com.example.my_first_spring_boot.entity.MasterBoardEntity;
import com.example.my_first_spring_boot.repository.BoardRepository;
import com.example.my_first_spring_boot.repository.MasterBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private final BoardRepository boardRepository;
    private final MasterBoardRepository masterBoardRepository;
    //게시글 목록을 불러오는 서비스
    public List<BoardEntity> findAllBoards() {
        return boardRepository.findAll();
    }
    //게시글 총 갯수를 불러오는 서비스
    public long totalBoards() { return boardRepository.count(); }
    //id에 해당하는 게시글을 불러오는 서비스
    public BoardEntity findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Board Id:"+id));
    }
    //게시글 조회시 조회수 증가 서비스
    public void increaseViews(BoardEntity boardEntity) {
        boardEntity.setViews(boardEntity.getViews() + 1);
        boardRepository.save(boardEntity);
    }
    //게시글 등록 서비스
    @Transactional
    public void saveBoard(BoardEntity boardEntity) {
        this.boardRepository.save(boardEntity);
    }
    //관리자,유저용 게시글 동시 등록 서비스
    @Transactional
    public void saveBoardWithMaster(BoardEntity boardEntity, MasterBoardEntity masterBoardEntity) {
        this.boardRepository.save(boardEntity);
        this.masterBoardRepository.save(masterBoardEntity);
    }
    //좋아요 버튼 서비스
    public void increaseLikes(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Board Id:"+id));
        boardEntity.setLikes(boardEntity.getLikes() + 1);
        boardRepository.save(boardEntity);
    }
    //게시글 삭제 서비스
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
    //게시글 ID로 게시글 가져오기
    public BoardEntity getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
    }
    //게시글 수정 서비스
    public void editBoard(Long id, BoardEntity editBoard) {
        BoardEntity boardEntity = getBoardById(id);
        boardEntity.setTitle(editBoard.getTitle());
        boardEntity.setContent(editBoard.getContent());
        boardRepository.save(boardEntity);
    }
    //생성자 주입
    public BoardService(BoardRepository boardRepository, MasterBoardRepository masterBoardRepository) {
        this.boardRepository = boardRepository;
        this.masterBoardRepository = masterBoardRepository;
    }
    //ID로 게시글 찾기
    public BoardEntity findById(Long id) {
        return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다"));
    }
    //게시글 최신순으로 정렬
    public List<BoardEntity> getAllBoards(){
        return boardRepository.findAllByOrderByCreateDateDesc();
    }
}
