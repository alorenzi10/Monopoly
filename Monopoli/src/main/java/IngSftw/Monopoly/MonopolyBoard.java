package IngSftw.Monopoly;

public class MonopolyBoard extends javax.swing.JPanel {

    public MonopolyBoard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Board = new javax.swing.JPanel();
        CentralBoard = new javax.swing.JPanel();
        VisitingJail = new javax.swing.JPanel();
        Go = new javax.swing.JPanel();
        GoToJail = new javax.swing.JPanel();
        BottomLine = new javax.swing.JPanel();
        TopLine = new javax.swing.JPanel();
        Parking = new javax.swing.JPanel();
        LeftColumn = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1600, 800));

        Board.setBackground(new java.awt.Color(51, 204, 0));
        Board.setPreferredSize(new java.awt.Dimension(800, 800));

        CentralBoard.setBackground(new java.awt.Color(102, 255, 0));
        CentralBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CentralBoard.setPreferredSize(new java.awt.Dimension(590, 590));

        javax.swing.GroupLayout CentralBoardLayout = new javax.swing.GroupLayout(CentralBoard);
        CentralBoard.setLayout(CentralBoardLayout);
        CentralBoardLayout.setHorizontalGroup(
            CentralBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        CentralBoardLayout.setVerticalGroup(
            CentralBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );

        VisitingJail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VisitingJail.setPreferredSize(new java.awt.Dimension(105, 105));

        javax.swing.GroupLayout VisitingJailLayout = new javax.swing.GroupLayout(VisitingJail);
        VisitingJail.setLayout(VisitingJailLayout);
        VisitingJailLayout.setHorizontalGroup(
            VisitingJailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );
        VisitingJailLayout.setVerticalGroup(
            VisitingJailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        Go.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Go.setPreferredSize(new java.awt.Dimension(105, 105));

        javax.swing.GroupLayout GoLayout = new javax.swing.GroupLayout(Go);
        Go.setLayout(GoLayout);
        GoLayout.setHorizontalGroup(
            GoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );
        GoLayout.setVerticalGroup(
            GoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        GoToJail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        GoToJail.setPreferredSize(new java.awt.Dimension(105, 105));

        javax.swing.GroupLayout GoToJailLayout = new javax.swing.GroupLayout(GoToJail);
        GoToJail.setLayout(GoToJailLayout);
        GoToJailLayout.setHorizontalGroup(
            GoToJailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );
        GoToJailLayout.setVerticalGroup(
            GoToJailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        // Set layout for BottomLine and add 9 panels
        BottomLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BottomLine.setPreferredSize(new java.awt.Dimension(590, 105));
        BottomLine.setLayout(new java.awt.GridLayout(1, 9)); // 1 row, 9 columns
        for (int i = 0; i < 9; i++) {
            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            BottomLine.add(panel);
        }

        // Set layout for TopLine and add 9 panels
        TopLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TopLine.setPreferredSize(new java.awt.Dimension(590, 105));
        TopLine.setLayout(new java.awt.GridLayout(1, 9)); // 1 row, 9 columns
        for (int i = 0; i < 9; i++) {
            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TopLine.add(panel);
        }

        Parking.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Parking.setPreferredSize(new java.awt.Dimension(105, 105));

        // Set layout for LeftColumn and add 9 panels
        LeftColumn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LeftColumn.setPreferredSize(new java.awt.Dimension(105, 590));
        LeftColumn.setLayout(new java.awt.GridLayout(9, 1)); // 9 rows, 1 column
        for (int i = 0; i < 9; i++) {
            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LeftColumn.add(panel);
        }

        // Set layout for jPanel2 (Right Column) and add 9 panels
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(105, 590));
        jPanel2.setLayout(new java.awt.GridLayout(9, 1)); // 9 rows, 1 column
        for (int i = 0; i < 9; i++) {
            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel2.add(panel);
        }

        javax.swing.GroupLayout BoardLayout = new javax.swing.GroupLayout(Board);
        Board.setLayout(BoardLayout);
        BoardLayout.setHorizontalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BoardLayout.createSequentialGroup()
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VisitingJail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Parking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LeftColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TopLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CentralBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BottomLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Go, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoToJail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        BoardLayout.setVerticalGroup(
            BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardLayout.createSequentialGroup()
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GoToJail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TopLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Parking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CentralBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LeftColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(VisitingJail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BottomLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Go, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Senza titolo-1.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    private javax.swing.JPanel Board;
    private javax.swing.JPanel BottomLine;
    private javax.swing.JPanel CentralBoard;
    private javax.swing.JPanel Go;
    private javax.swing.JPanel GoToJail;
    private javax.swing.JPanel LeftColumn;
    private javax.swing.JPanel Parking;
    private javax.swing.JPanel TopLine;
    private javax.swing.JPanel VisitingJail;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
}
