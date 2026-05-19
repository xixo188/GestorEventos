package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GestorEventosApp extends JFrame {

    public GestorEventosApp() {
        setTitle("Gestão de Eventos Universitários");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showEventListScreen();
    }

    private void showEventListScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Eventos", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JButton createButton = new JButton("Criar Evento");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(createButton);

        JPanel eventsPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        eventsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        for (int i = 1; i <= 8; i++) {
            eventsPanel.add(createEventCard("Evento " + i, "Data"));
        }

        createButton.addActionListener(e -> showNewEventScreen());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(eventsPanel, BorderLayout.CENTER);

        panel.add(title, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(panel);
        revalidate();
        repaint();
    }

    private JPanel createEventCard(String name, String date) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);

        JLabel image = new JLabel("Imagem", SwingConstants.CENTER);
        image.setPreferredSize(new Dimension(150, 100));
        image.setMaximumSize(new Dimension(150, 100));
        image.setOpaque(true);
        image.setBackground(Color.LIGHT_GRAY);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        JLabel dateLabel = new JLabel(date);

        JButton detailsButton = new JButton("Ver detalhes");
        JButton registerButton = new JButton("Inscrever");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(detailsButton);
        buttonsPanel.add(registerButton);

        card.add(image);
        card.add(nameLabel);
        card.add(dateLabel);
        card.add(buttonsPanel);

        return card;
    }

    private void showNewEventScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Novo evento", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        JLabel imageLabel = new JLabel("Clique para escolher imagem", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 250));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showOpenDialog(GestorEventosApp.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

                    Image image = imageIcon.getImage().getScaledInstance(
                            300,
                            250,
                            Image.SCALE_SMOOTH
                    );

                    imageLabel.setText("");
                    imageLabel.setIcon(new ImageIcon(image));
                }
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

        JTextField nomeField = new JTextField();
        JTextField dataHoraField = new JTextField();
        JTextField apresentadorField = new JTextField();
        JTextField instituicaoField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField duracaoField = new JTextField();
        JTextField tipoEventoField = new JTextField();
        JTextField lotacaoField = new JTextField();
        JTextField estadoField = new JTextField();
        JTextField dataLimiteField = new JTextField();
        JTextArea descricaoArea = new JTextArea(3, 20);

        formPanel.add(new JLabel("Nome *"));
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Data/Hora *"));
        formPanel.add(dataHoraField);

        formPanel.add(new JLabel("Apresentador *"));
        formPanel.add(apresentadorField);

        formPanel.add(new JLabel("Instituição *"));
        formPanel.add(instituicaoField);

        formPanel.add(new JLabel("Categoria *"));
        formPanel.add(categoriaField);

        formPanel.add(new JLabel("Duração *"));
        formPanel.add(duracaoField);

        formPanel.add(new JLabel("Tipo de Evento *"));
        formPanel.add(tipoEventoField);

        formPanel.add(new JLabel("Lotação Máxima *"));
        formPanel.add(lotacaoField);

        formPanel.add(new JLabel("Estado *"));
        formPanel.add(estadoField);

        formPanel.add(new JLabel("Data Limite Inscrição *"));
        formPanel.add(dataLimiteField);

        formPanel.add(new JLabel("Descrição"));
        formPanel.add(new JScrollPane(descricaoArea));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.add(imageLabel, BorderLayout.WEST);
        centerPanel.add(formPanel, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton confirmarButton = new JButton("Confirmar");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(voltarButton);
        buttonPanel.add(cancelarButton);
        buttonPanel.add(confirmarButton);

        voltarButton.addActionListener(e -> showEventListScreen());

        cancelarButton.addActionListener(e -> {
            clearFields(
                    nomeField, dataHoraField, apresentadorField, instituicaoField,
                    categoriaField, duracaoField, tipoEventoField, lotacaoField,
                    estadoField, dataLimiteField, descricaoArea
            );

            imageLabel.setText("Clique para escolher imagem");
            imageLabel.setIcon(null);
        });

        confirmarButton.addActionListener(e -> {
            if (
                    nomeField.getText().isBlank() ||
                            dataHoraField.getText().isBlank() ||
                            apresentadorField.getText().isBlank() ||
                            instituicaoField.getText().isBlank() ||
                            categoriaField.getText().isBlank() ||
                            duracaoField.getText().isBlank() ||
                            tipoEventoField.getText().isBlank() ||
                            lotacaoField.getText().isBlank() ||
                            estadoField.getText().isBlank() ||
                            dataLimiteField.getText().isBlank()
            ) {
                JOptionPane.showMessageDialog(
                        this,
                        "Preenche todos os campos obrigatórios assinalados com *.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Evento criado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showEventListScreen();
            }
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        revalidate();
        repaint();
    }

    private void clearFields(JTextField nome, JTextField dataHora, JTextField apresentador,
                             JTextField instituicao, JTextField categoria, JTextField duracao,
                             JTextField tipoEvento, JTextField lotacao, JTextField estado,
                             JTextField dataLimite, JTextArea descricao) {

        nome.setText("");
        dataHora.setText("");
        apresentador.setText("");
        instituicao.setText("");
        categoria.setText("");
        duracao.setText("");
        tipoEvento.setText("");
        lotacao.setText("");
        estado.setText("");
        dataLimite.setText("");
        descricao.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestorEventosApp().setVisible(true);
        });
    }
}