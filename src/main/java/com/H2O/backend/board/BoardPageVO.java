package com.H2O.backend.board;


import lombok.Data;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;

@Data
public class BoardPageVO {
    private long totoalPages;
    private long totalBoards;
    private List<RequestedBoardsVO> boards;
}
