import { OthelloClientPage } from './app.po';

describe('othello-client App', function() {
  let page: OthelloClientPage;

  beforeEach(() => {
    page = new OthelloClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
