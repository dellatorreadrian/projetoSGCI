package br.edu.iff.projetoSGCI;

import br.edu.iff.projetoSGCI.model.Atendente;
import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.model.Cliente;
import br.edu.iff.projetoSGCI.model.CriticidadeEnum;
import br.edu.iff.projetoSGCI.model.InformacaoHistorico;
import br.edu.iff.projetoSGCI.model.NivelAtendenteEnum;
import br.edu.iff.projetoSGCI.model.Servidor;
import br.edu.iff.projetoSGCI.model.StatusChamadoEnum;
import br.edu.iff.projetoSGCI.model.StatusServidorEnum;
import br.edu.iff.projetoSGCI.repository.AtendenteRepository;
import br.edu.iff.projetoSGCI.repository.ChamadoRepository;
import br.edu.iff.projetoSGCI.repository.ClienteRepository;
import br.edu.iff.projetoSGCI.repository.InformacaoHistoricoRepository;
import br.edu.iff.projetoSGCI.repository.ServidorRepository;
import static java.time.OffsetTime.now;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoSgciApplication implements CommandLineRunner{

    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private AtendenteRepository atendenteRepo;
    @Autowired
    private ChamadoRepository chamadoRepo;
    @Autowired
    private ServidorRepository servidorRepo;
    @Autowired
    private InformacaoHistoricoRepository informacaoHistoricoRepo;
    
    public static void main(String[] args) {
	SpringApplication.run(ProjetoSgciApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cliente c1 = new Cliente();
        c1.setNome("Jose das Couves");
        c1.setLogin("josedc");
        c1.setSetor("Contabilidade");
        c1.setSenha("12345678");
        
        clienteRepo.save(c1);
        
        Atendente a1 = new Atendente();
        a1.setNome("Adrian");
        a1.setLogin("adrianld");
        a1.setNivel(NivelAtendenteEnum.PLENO);
        a1.setSenha("a1b2c3d4");
        
        atendenteRepo.save(a1);
        
        Servidor s1 = new Servidor();
        s1.setIp("192.168.1.25");
        s1.setNome("Yoda");
        s1.setStatus(StatusServidorEnum.ONLINE);
        
        servidorRepo.save(s1);
        
        InformacaoHistorico i1 = new InformacaoHistorico();
        i1.setAtendente(a1);
        i1.setData(Calendar.getInstance());
        i1.setTexto("Designando chamado pela primeira vez.");
        
        //informacaoHistoricoRepo.save(i1);
        
        InformacaoHistorico i2 = new InformacaoHistorico();
        i2.setAtendente(a1);
        i2.setData(Calendar.getInstance());
        i2.setTexto("Favor verificar.");
        
        //informacaoHistoricoRepo.save(i2);
        
        Chamado ch1 = new Chamado();
        ch1.setAtendente(a1);
        ch1.setCliente(c1);
        ch1.setConclusao(null);
        ch1.setCriticidade(CriticidadeEnum.MEDIA);
        ch1.setDataAbertura(Calendar.getInstance());
        ch1.setDescricao("Chamado de Teste para os Repositorios");
        ch1.setStatus(StatusChamadoEnum.DESIGNADO);
        ch1.setServidor(s1);
        ch1.setInformacoesHistorico(List.of(i1,i2));
        Calendar prazo = Calendar.getInstance();
        prazo.add(Calendar.DATE, 2);
        ch1.setPrazo(prazo);
        
        chamadoRepo.save(ch1);
    }

}
