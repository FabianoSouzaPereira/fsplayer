# Sobre o Player
O player usa uma versão completa com PlayerActivity + PlayerScreen + PlayerViewModel com fullscreen, PiP e thumbnails funcionando juntos.

## Estrutura do player

- Esse PlayerActivity já cobre:
- ExoPlayer integrado em Compose.
- Botão fullscreen → muda orientação dinamicamente.
- Picture-in-Picture automático ao sair do app.
- Suporte a tablets/dobráveis sem letterboxing, porque não fixamos screenOrientation no Manifest.

## Como fica a arquitetura

- MainActivity → controla navegação Compose (Splash, Login, Home, Settings).
- PlayerActivity → totalmente isolada, só é aberta com startActivity.
- Features continuam organizadas em seus próprios módulos/pacotes (features.home, features.player, etc).

## Chamamos as funções do ExoPlayer

É porque o ExoPlayer tem o módulo PreviewSeekBar no pacote UI, mas como você está no Compose, você provavelmente vai ter que
recriar o controle manualmente, integrando com o ViewModel para baixar e exibir os thumbnails.

## 🔑 Estratégia n o carregamenteo de thumbnails

1. Escutar posição da timeline enquanto o usuário arrasta

- O Player expõe onPositionDiscontinuity e você também pode usar um Slider do Compose controlado manualmente.
- Enquanto o usuário mantém o dedo pressionado e move, você atualiza a posição que quer pré-visualizar.

2. Gerar/baixar thumbnail (preview frame)

- Se o vídeo é local: usar MediaMetadataRetriever para extrair o frame no timestamp que o usuário está apontando.
- Se o vídeo é online (streaming):
- Normalmente os backends geram sprite sheets (um JPG/PNG gigante com vários thumbs) + um arquivo .vtt indicando os intervalos.
- Você pode baixar isso via Glide (ou Coil) e renderizar apenas a região correspondente ao tempo atual.
- O ExoPlayer tem suporte a isso via DefaultTimeBar.setAdGroupTimesMs(...), mas no Compose você teria que implementar manualmente
  o preview.

3. Exibir em overlay acima da timeline

- Criar um Box com o Slider + um Image posicionado dinamicamente acima do polegar (thumb) do slider.
- O Image mostra o thumbnail atual carregado.

## VideoTimeWithPreview

- Cache de thumbnails (thumbnailCache) → evita buscar várias vezes a mesma posição.
- Slider sincronizado com player → LaunchedEffect atualiza sliderPosition e duration.
- Posição do thumbnail proporcional ao slider → converte ms do vídeo para pixels usando sliderWidthPx.
- Thumbnail acima do polegar → centralizado com offset.
- ThumbnailsProvider agora é suspending → evita produceState a cada recomposição.

## Media3 (androidx.media3)

Vantagens:

- Manutenção oficial pelo Google – Media3 é o caminho recomendado para Android agora, recebe atualizações no Jetpack.
- API moderna e consistente com Jetpack – mais fácil integração com Compose, Lifecycle, ViewModel, etc.
- Compatibilidade futura – garante que novas features do Android, como Picture-in-Picture ou DRM, sejam suportadas oficialmente.
- Integração com serviços de mídia – Media3 inclui módulos de UI, exoplayer, sessão de mídia, compatibilidade com MediaSession, PlayerControl, etc.

Desvantagens:

- API mais nova, algumas classes são @UnstableApi – precisa usar @OptIn(UnstableApi::class) para certas funcionalidades, como AspectRatioFrameLayout.
- Menos exemplos/documentação comparado ao ExoPlayer clássico, embora a maioria do código seja muito parecido.
- Migração de projetos antigos pode exigir pequenas mudanças, principalmente nas constantes e pacotes (RESIZE_MODE, PlayerView, etc.).

## Exoplayer no ViewModel

O ExoPlayer vive dentro do ViewModel, ele vira o único ponto de verdade (single source of truth) para manipular o player.

### Por que isso é bom?

- Ciclo de vida mais limpo
   O ViewModel sobrevive a rotação, mudanças de configuração etc. Se o player estivesse só no Activity, você perderia o estado a cada recriação.

- UI desacoplada da lógica
   O Activity/Composable só exibe UI e manda eventos pro ViewModel.
   Toda lógica de play/pause, seek, fullscreen, pip, volume, legendas... fica centralizada no ViewModel.

- Mais testável
   Você pode testar a lógica do ViewModel isolada da UI.